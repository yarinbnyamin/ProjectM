package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{
    private Stack<Position3D> s = new Stack<>();
    private Random rand = new Random();
    private int rows;
    private int columns;
    private  int depths;
    private boolean ended;
    @Override
    public Maze3D generate(int depth, int row, int column) {
        this.rows = row;
        this.columns = column;
        this.depths=depth;
        int r;
        int c;
        int d;
        int count = 0; // how fill the maze is
        Maze3D m3d;
        do {
           m3d=new Maze3D(depths,rows,columns);
            int[][][] m = m3d.getMap();
            allNumGenerateor(m3d, 1);
            s.push(new Position3D(0,0,0));
            Position3D curr = s.peek();
            ArrayList<Position3D> neighbors;
            do {
                r = curr.getRowIndex();
                c = curr.getColumnIndex();
                d = curr.getDepthIndex();
                m[d][r][c] = 1;
                neighbors = findNeighbors(curr,m3d);
                m[d][r][c] = 0;
                randomlyAddCellToStack(neighbors);
                count++;
                curr = s.pop();
            }while (!s.empty() && !(endPoint(d,r, c,m3d)));
            s.clear();
            if(count < d*r*c*0.75) {// if the maze not full enough
                ended = false;
                count=0;
            }
        }while(!ended);
        m3d.getMap()[depths-1][rows-1][columns-1]=0;
        return m3d;
    }
    private ArrayList<Position3D> findNeighbors(Position3D pos,Maze3D M) {
        // return a list with all the possible paths
        ArrayList<Position3D> neighbors = new ArrayList<>();

        int c = pos.getColumnIndex();
        int r = pos.getRowIndex();
        int d = pos.getDepthIndex();

        if(validPos(d,r-1, c) && validMov(d,r-1, c, M)){
            neighbors.add(new Position3D(d,r-1, c));
        }

        if(validPos(d,r, c-1) && validMov(d,r, c-1, M)){
            neighbors.add(new Position3D(d,r, c-1));
        }

        if(validPos(d,r+1, c) && validMov(d,r+1, c, M)){
            neighbors.add(new Position3D(d,r+1, c));
        }

        if(validPos(d,r, c+1) && validMov(d,r, c+1, M)){
            neighbors.add(new Position3D(d,r, c+1));
        }
        if(validPos(d+1,r, c) && validMov(d+1,r, c, M)){
            neighbors.add(new Position3D(d+1,r, c));
        }
        if(validPos(d-1,r, c) && validMov(d-1,r, c, M)){
            neighbors.add(new Position3D(d-1,r, c));
        }
        return neighbors;
    }

    private Boolean validMov(int d,int r, int c, Maze3D M){
        // if the position neighbors is not 0
        int[][][] m = M.getMap();
        boolean flag;
        flag = m[d][r][c] == 1;
        if(validPos(d,r-1, c))
            flag = flag && (m[d][r-1][c] == 1);
        if(validPos(d,r+1, c))
            flag = flag && (m[d][r+1][c] == 1);
        if(validPos(d,r, c-1))
            flag = flag && (m[d][r][c-1] == 1);
        if(validPos(d,r, c+1))
            flag = flag && (m[d][r][c+1] == 1);
        if (validPos(d+1,r,c))
            flag = flag && (m[d+1][r][c] == 1);
        if (validPos(d-1,r,c))
            flag = flag && (m[d-1][r][c] == 1);
        return flag;
    }

    private Boolean validPos(int d, int r,int c){
        // if the point is in the maze
        return c >= 0 && c < columns && r >= 0 && r < rows && d>=0 && d<depths;
    }
    private void randomlyAddCellToStack(ArrayList<Position3D> cells) {
        // chose random paths from list of paths
        if(cells.isEmpty())
            return;
        Position3D pos = cells.get(rand.nextInt(cells.size()));
        s.push(pos);
        s.push(pos);
    }
    private boolean endPoint(int d,int r, int c,Maze3D M){
        // if we got to the end point or one step near it
        Position3D endPos = M.getGoalPosition();
        int rE = endPos.getRowIndex();
        int cE = endPos.getColumnIndex();
        int dE=endPos.getDepthIndex();
        boolean flag;
        flag = ((r == rE) && (c == cE))&&(d==dE);
        flag = flag || ((r+1 == rE) && (c == cE))&&(d==dE);
        flag = flag || ((r == rE) && (c+1 == cE))&&(d==dE);
        flag = flag || ((r == rE) && (c == cE))&&(d+1==dE);
        flag = flag || ((r == rE) && (c == cE))&&(d-1==dE);


        if(flag)
            ended = true;
        return flag;
    }

}

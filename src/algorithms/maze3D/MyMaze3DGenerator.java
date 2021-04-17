package algorithms.maze3D;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{
    private Stack<Position3D> s = new Stack<>();
    private Random rand = new Random();
    private double how_full; // we ended to generate complex

    /**
     * @param depth
     * @param row
     * @param column
     * @return a 3D maze with at least one path for a solution
     * @throws Exception
     */
    @Override
    public Maze3D generate(int depth, int row, int column) throws Exception{
        how_full = 1;// a variable that represent the value of the "complexity"\"fullness" of the maze generated
        int r;
        int c;
        int d;
        int count; // counts the "0"'s (non walls cells) that the maze generates
        Maze3D m3d;
        do {
            how_full -= 0.1;
            count=0;
            m3d=new Maze3D(depth,row,column);
            int[][][] m = m3d.getMap();
            allNumGenerateor(m3d, 1);//generates a maze and fill it with walls only.
            s.push(new Position3D(0,0,0));
            Position3D curr = s.peek();
            ArrayList<Position3D> neighbors;
            do {
                r = curr.getRowIndex();
                c = curr.getColumnIndex();
                d = curr.getDepthIndex();
                m[d][r][c] = 1;
                neighbors = findNeighbors(curr,m3d);//finds all the possible positions that the maze can expend into.
                m[d][r][c] = 0;//sets the current position as path
                randomlyAddCellToStack(neighbors);
                count++;
                curr = s.pop();
            }while (!s.empty() && !(endPoint(d,r, c,m3d)));
            s.clear();
        }while(count < row*column*depth*how_full || !endPoint(d, r, c, m3d));//if the maze is not "complex" enough make a new one
        m3d.getMap()[depth-1][row-1][column-1]=0;
        return m3d;
    }

    /**
     * @param pos current position
     * @param M a 3D maze
     * @return a list of all the positions that a state can expend into.
     */
    private ArrayList<Position3D> findNeighbors(Position3D pos,Maze3D M) {
        // return a list with all the possible paths
        ArrayList<Position3D> neighbors = new ArrayList<>();

        int c = pos.getColumnIndex();
        int r = pos.getRowIndex();
        int d = pos.getDepthIndex();

        if(M.validPos(d,r-1, c) && validMov(d,r-1, c, M)){
            neighbors.add(new Position3D(d,r-1, c));
        }

        if(M.validPos(d,r, c-1) && validMov(d,r, c-1, M)){
            neighbors.add(new Position3D(d,r, c-1));
        }

        if(M.validPos(d,r+1, c) && validMov(d,r+1, c, M)){
            neighbors.add(new Position3D(d,r+1, c));
        }

        if(M.validPos(d,r, c+1) && validMov(d,r, c+1, M)){
            neighbors.add(new Position3D(d,r, c+1));
        }
        if(M.validPos(d+1,r, c) && validMov(d+1,r, c, M)){
            neighbors.add(new Position3D(d+1,r, c));
        }
        if(M.validPos(d-1,r, c) && validMov(d-1,r, c, M)){
            neighbors.add(new Position3D(d-1,r, c));
        }
        return neighbors;
    }

    /**
     * @param d Depth
     * @param r Row
     * @param c Column
     * @param M a 3D maze
     * @return if a move into a neighbor cell is valid
     */
    private Boolean validMov(int d,int r, int c, Maze3D M){
        // if the position neighbors is not 0
        int[][][] m = M.getMap();
        boolean flag; // a variable that checks if the neighbor cell being expended into is surrounded with walls
        flag = m[d][r][c] == 1;
        if(M.validPos(d,r-1, c))
            flag = flag && (m[d][r-1][c] == 1);
        if(M.validPos(d,r+1, c))
            flag = flag && (m[d][r+1][c] == 1);
        if(M.validPos(d,r, c-1))
            flag = flag && (m[d][r][c-1] == 1);
        if(M.validPos(d,r, c+1))
            flag = flag && (m[d][r][c+1] == 1);
        if (M.validPos(d+1,r,c))
            flag = flag && (m[d+1][r][c] == 1);
        if (M.validPos(d-1,r,c))
            flag = flag && (m[d-1][r][c] == 1);
        return flag;
    }

    /**randomly Adds Cells To Stack of positions
     * @param cells list of positions
     */
    private void randomlyAddCellToStack(ArrayList<Position3D> cells) {
        // chose random paths from list of paths
        if(cells.isEmpty())
            return;
        Position3D pos = cells.get(rand.nextInt(cells.size()));
        s.push(pos);
        s.push(pos);
    }

    /**
     * @param d Depth
     * @param r Row
     * @param c Column
     * @param M a 3D maze
     * @return if the current current position is the Goal position or one step near it
     */
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

        return flag;
    }

}

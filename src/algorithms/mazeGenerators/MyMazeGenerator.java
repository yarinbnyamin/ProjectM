package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    private Stack<Position> s = new Stack<>(); // the path we pass
    private Random rand = new Random();
    private int rows;
    private int columns;
    private double how_full; // we ended to generate complex

    @Override
    public Maze generate(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        how_full = 1;
        int r;
        int c;
        int count; // how fill the maze is
        Maze M;
        do{
            how_full -= 0.1;
            count=0;
            M = new Maze(rows,columns);
            int[][] m = M.getMaze();
            allNumGenerateor(M, 1); // start with all one's
            s.push(new Position(0,0));
            Position curr = s.peek(); // start from the starting position
            ArrayList<Position> neighbors;
            do {
                r = curr.getRowIndex();
                c = curr.getColumnIndex();
                m[r][c] = 1;
                // check all the available path we can take
                neighbors = findNeighbors(curr,M);
                m[r][c] = 0;
                // chose only one path
                randomlyAddCellToStack(neighbors);
                count++;
                curr = s.pop();
            } while (!s.empty() && !(endPoint(r, c, M)));
            s.clear();
        }while(count < rows*columns*how_full || !endPoint(r, c, M)); // we we ended the generate but we didn't got to the end
        M.getMaze()[rows-1][columns-1]=0;
        return M;

    }

    private ArrayList<Position> findNeighbors(Position pos,Maze M) {
        // return a list with all the possible paths
        ArrayList<Position> neighbors = new ArrayList<>();

        int c = pos.getColumnIndex();
        int r = pos.getRowIndex();

        if(validPos(r-1, c) && validMov(r-1, c, M)){
            neighbors.add(new Position(r-1, c));
        }

        if(validPos(r, c-1) && validMov(r, c-1, M)){
            neighbors.add(new Position(r, c-1));
        }

        if(validPos(r+1, c) && validMov(r+1, c, M)){
            neighbors.add(new Position(r+1, c));
        }

        if(validPos(r, c+1) && validMov(r, c+1, M)){
            neighbors.add(new Position(r, c+1));
        }

        return neighbors;
    }

    private Boolean validPos(int r, int c){
        // if the point is in the maze
        return c >= 0 && c < columns && r >= 0 && r < rows;
    }

    private Boolean validMov(int r, int c, Maze M){
        // if the position neighbors is not 0
        int[][] m = M.getMaze();
        boolean flag;

        flag = m[r][c] == 1;
        if(validPos(r-1, c))
            flag = flag && (m[r-1][c] == 1);
        if(validPos(r+1, c))
            flag = flag && (m[r+1][c] == 1);
        if(validPos(r, c-1))
            flag = flag && (m[r][c-1] == 1);
        if(validPos(r, c+1))
            flag = flag && (m[r][c+1] == 1);

        return flag;
    }

    private boolean endPoint(int r, int c,Maze M){
        // if we got to the end point or one step near it
        Position endPos = M.getGoalPosition();
        int rE = endPos.getRowIndex();
        int cE = endPos.getColumnIndex();
        boolean flag;

        flag = ((r == rE) && (c == cE));
        flag = flag || ((r+1 == rE) && (c == cE));
        flag = flag || ((r == rE) && (c+1 == cE));
        //flag = flag || (r-1 == rE) && (c == cE);
        //flag = flag || (r == rE) && (c-1== cE);

        return flag;
    }

    private void randomlyAddCellToStack(ArrayList<Position> cells) {
        // chose random paths from list of paths
        if(cells.isEmpty())
            return;

        Position pos = cells.get(rand.nextInt(cells.size()));
        s.push(pos);
        s.push(pos);
    }
}

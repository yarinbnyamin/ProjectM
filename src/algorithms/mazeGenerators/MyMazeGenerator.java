package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{
    private Stack<Position> s = new Stack<>();
    private Stack<Position> path = new Stack<>();
    private Random rand = new Random();
    private int rows;
    private int columns;
    private boolean ended;

    @Override
    public Maze generate(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        int r;
        int c;
        int count = 0;
        Maze M;
        do{
            M = new Maze(rows,columns);
            int[][] m = M.getMaze();
            allWallsGenerateor(M);
            s.push(new Position(0,0));
            Position curr = s.peek();
            ArrayList<Position> neighbors;
            do {
                r = curr.getRowIndex();
                c = curr.getColumnIndex();
                m[r][c] = 1;
                neighbors = findNeighbors(curr,M);
                m[r][c] = 0;
                randomlyAddCellToStack(neighbors);
                count++;
                curr = s.pop();
            } while (!s.empty() && !(endPoint(r, c, M)));
            s.clear();
            if(count < r*c*0.35)
                ended = false;
        }while(!ended);

        return M;

    }

    private void allWallsGenerateor(Maze M){

        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getColumns(); j++)
                M.getMaze()[i][j] = 1;

    }

    private ArrayList<Position> findNeighbors(Position pos,Maze M) {
        ArrayList<Position> neighbors = new ArrayList<Position>();

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
        if(c >= 0 && c < columns && r >= 0 && r < rows)
            return true;
        return false;
    }

    private Boolean validMov(int r, int c, Maze M){
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
        Position endPos = M.getGoalPosition();
        int rE = endPos.getRowIndex();
        int cE = endPos.getColumnIndex();
        boolean flag;

        flag = ((r == rE) && (c == cE));
        flag = flag || ((r+1 == rE) && (c == cE));
        flag = flag || ((r == rE) && (c+1 == cE));
        //flag = flag || (r-1 == rE) && (c == cE);
        //flag = flag || (r == rE) && (c-1== cE);

        if(flag)
            ended = true;

        return flag;
    }

    private void randomlyAddCellToStack(ArrayList<Position> cells) {
        if(cells.isEmpty())
            return;

        Position pos = cells.get(rand.nextInt(cells.size()));
        s.push(pos);
        s.push(pos);
    }
}

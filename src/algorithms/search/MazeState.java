package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class MazeState extends AState{
    private Maze maze;
    Position pos;

    public MazeState(AState cameFrom, Maze maze, Position pos) {
        super(cameFrom);
        this.maze = maze;
        this.pos = pos;
        state = pos.toString();
        if(cameFrom != null) { // set the cost of the state
            if (isDiagonal(((MazeState) cameFrom).getPos(), pos))
                cost = cameFrom.getCost() + Math.sqrt(2);
            else
                cost = cameFrom.getCost() + 1;
        }else{
            cost = 0;
        }
    }

    /**
     * @return all the valid neighbors of the this maze state
     */
    public ArrayList<Position> getNeighbors() {

        int[][] m = maze.getMaze();
        ArrayList<Position> posList = new ArrayList<>();
        int r = pos.getRowIndex(), c = pos.getColumnIndex();

        // check all the valid neighbors
        if(validPos(r-1, c) && m[r-1][c] == 0)
            posList.add(new Position(r-1,c));
        if(validPos(r-1, c+1) && m[r-1][c+1] == 0 && (m[r-1][c] == 0 || m[r][c+1] == 0))
            posList.add(new Position(r-1,c+1));
        if(validPos(r, c+1) && m[r][c+1] == 0)
            posList.add(new Position(r,c+1));
        if(validPos(r+1, c+1) && m[r+1][c+1] == 0 && (m[r+1][c] == 0 || m[r][c+1] == 0))
            posList.add(new Position(r+1,c+1));
        if(validPos(r+1, c) && m[r+1][c] == 0)
            posList.add(new Position(r+1,c));
        if(validPos(r+1, c-1) && m[r+1][c-1] == 0 && (m[r+1][c] == 0 || m[r][c-1] == 0))
            posList.add(new Position(r+1,c-1));
        if(validPos(r, c-1) && m[r][c-1] == 0)
            posList.add(new Position(r,c-1));
        if(validPos(r-1, c-1) && m[r-1][c-1] == 0 && (m[r-1][c] == 0 || m[r][c-1] == 0))
            posList.add(new Position(r-1,c-1));


        return  posList;
    }

    private Boolean validPos(int r, int c){
        // if the point is in the maze
        return c >= 0 && c < maze.getColumns() && r >= 0 && r < maze.getRows();
    }


    /**
     * @param from the position we came from
     * @param to the position we go to
     * @return if the move is diagonal move
     */
    private boolean isDiagonal(Position from, Position to){
        int Rfrom = from.getRowIndex();
        int Cfrom = from.getColumnIndex();
        int Rto = to.getRowIndex();
        int Cto = to.getColumnIndex();

        if (Rfrom > Rto && Cfrom > Cto)
            return true;
        if (Rfrom > Rto && Cfrom < Cto)
            return true;
        if (Rfrom < Rto && Cfrom < Cto)
            return true;
        return Rfrom < Rto && Cfrom > Cto;

    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}

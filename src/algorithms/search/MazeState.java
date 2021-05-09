package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
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
        if(maze.validPos(r-1, c) && m[r-1][c] == 0)
            posList.add(new Position(r-1,c));
        if(maze.validPos(r-1, c+1) && m[r-1][c+1] == 0 && (m[r-1][c] == 0 || m[r][c+1] == 0))
            posList.add(new Position(r-1,c+1));
        if(maze.validPos(r, c+1) && m[r][c+1] == 0)
            posList.add(new Position(r,c+1));
        if(maze.validPos(r+1, c+1) && m[r+1][c+1] == 0 && (m[r+1][c] == 0 || m[r][c+1] == 0))
            posList.add(new Position(r+1,c+1));
        if(maze.validPos(r+1, c) && m[r+1][c] == 0)
            posList.add(new Position(r+1,c));
        if(maze.validPos(r+1, c-1) && m[r+1][c-1] == 0 && (m[r+1][c] == 0 || m[r][c-1] == 0))
            posList.add(new Position(r+1,c-1));
        if(maze.validPos(r, c-1) && m[r][c-1] == 0)
            posList.add(new Position(r,c-1));
        if(maze.validPos(r-1, c-1) && m[r-1][c-1] == 0 && (m[r-1][c] == 0 || m[r][c-1] == 0))
            posList.add(new Position(r-1,c-1));


        return  posList;
    }


    /**
     * @param from the position we came from
     * @param to the position we go to
     * @return if the move is diagonal move
     */
    private boolean isDiagonal(Position from, Position to){
        int RowFrom = from.getRowIndex();
        int ColFrom = from.getColumnIndex();
        int RowTo = to.getRowIndex();
        int ColTo = to.getColumnIndex();

        if (RowFrom > RowTo && ColFrom > ColTo)
            return true;
        if (RowFrom > RowTo && ColFrom < ColTo)
            return true;
        if (RowFrom < RowTo && ColFrom < ColTo)
            return true;
        return RowFrom < RowTo && ColFrom > ColTo;

    }


    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(pos);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        pos = (Position) stream.readObject();
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

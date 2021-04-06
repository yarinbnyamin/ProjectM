package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;

    public SearchableMaze(Maze m) {
        maze = m;
    }

    @Override
    public AState getStartState() {
        MazeState state = new MazeState(null, maze, maze.getStartPosition());
        state.setCost(0);
        return state;
    }

    @Override
    public AState getGoalState() {
        return new MazeState(null, maze, maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {

        ArrayList<AState> successors = new ArrayList<>();
        MazeState successorState;
        MazeState mazeState = (MazeState)s;
        ArrayList<Position> successorsPositions = mazeState.getNeighbors();

        for (Position pos : successorsPositions) {

            successorState = new MazeState(s, maze, pos);
            successorState.setCost(s.getCost()+1);
            successors.add(successorState);
        }

        return successors;
    }

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
/*
    public boolean isDiagonal() {
        return diagonal;
    }

    public void setDiagonal(boolean diagonal) {
        this.diagonal = diagonal;
    }

 */
}

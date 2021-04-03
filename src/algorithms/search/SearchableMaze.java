package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable{
    private Maze maze;
    //private State[][] stateMaze;
    private boolean diagonal;

    public SearchableMaze(Maze m) {
        maze = m;
        this.diagonal = false;
        /*
        stateMaze = new State[maze.getRows()][maze.getColumns()];
        for (int i = 0; i <maze.getRows() ; i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                stateMaze[i][j] = State.white;
            }
        }
         */
    }

    @Override
    public AState getStartState() {
        MazeState state = new MazeState(null, maze);
        state.setCost(0);
        return state;
    }

    @Override
    public AState getGoalState() {
        MazeState state = new MazeState(null, maze, maze.getGoalPosition());
        state.setState(State.goal);
        return state;
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {

        ArrayList<AState> successors = new ArrayList<>();
        MazeState successorState;
        MazeState mazeState = (MazeState)s;
        ArrayList<Position> successorsPositions = mazeState.getNeighbors(diagonal);

        s.setState(State.grey);
        for (Position pos : successorsPositions) {

            successorState = new MazeState(s, maze, pos);
            if (isDiagonal(mazeState.getPos(), pos))
                successorState.setCost(s.getCost()+1.5);
            else
                successorState.setCost(s.getCost()+1);

            if(successorState.getState() == State.white)
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
        if (Rfrom < Rto && Cfrom > Cto)
            return true;
        return false;

    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public boolean isDiagonal() {
        return diagonal;
    }

    public void setDiagonal(boolean diagonal) {
        this.diagonal = diagonal;
    }
/*
    public possibleState[][] getMazeState() {
        return mazeState;
    }

    public void setMazeState(possibleState[][] mazeState) {
        this.mazeState = mazeState;
    }

    public void setState(MazeState point, possibleState st){
        int r = point.getPos().getRowIndex();
        int c = point.getPos().getColumnIndex();
        mazeState[r][c] = st;
    }

    public possibleState getState(MazeState point){
        int r = point.getPos().getRowIndex();
        int c = point.getPos().getColumnIndex();
        return mazeState[r][c];
    }
    */
}

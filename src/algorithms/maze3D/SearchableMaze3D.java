package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class SearchableMaze3D implements ISearchable {
    private Maze3D maze;

    public SearchableMaze3D(Maze3D maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        Maze3DState state = new Maze3DState(null,maze,maze.getStartPosition());
        state.setCost(0);
        return state;
    }

    @Override
    public AState getGoalState() {
        return new Maze3DState(null, maze, maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> successors = new ArrayList<>();
        Maze3DState successorState;
        Maze3DState mazeState = (Maze3DState)s;
        ArrayList<Position3D> successorsPositions = mazeState.getNeighbors();
        for (Position3D pos : successorsPositions) {
            successorState = new Maze3DState(s, maze, pos);
            successorState.setCost(s.getCost()+1);
            successors.add(successorState);
        }
        return successors;
    }

    public Maze3D getMaze() {
        return maze;
    }

    public void setMaze(Maze3D maze) {
        this.maze = maze;
    }
}

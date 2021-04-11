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
        ArrayList<Position> successorsPositions = ((MazeState)s).getNeighbors();

        for (Position pos : successorsPositions) {
            successors.add(new MazeState(s, maze, pos));
        }

        return successors;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

}

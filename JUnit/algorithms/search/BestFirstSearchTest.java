package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    IMazeGenerator mg;
    ISearchingAlgorithm best = new BestFirstSearch();


    @Test
    public void diagonalFullZero() throws Exception {
        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < 100; i++) {
            assertEquals("{" + i + "," + i + "}",solutionPath.get(i).toString());
        }
    }

    @Test
    void noExit() throws Exception {
        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(2, 2);
        maze.getMaze()[0][0] = 1;
        maze.getMaze()[0][1] = 1;
        maze.getMaze()[1][0] = 1;
        maze.getMaze()[1][1] = 1;
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        assertNull(solution);
    }

    @Test
    void noValidDiagonal() throws Exception {
        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(3, 3);
        maze.getMaze()[0][2] = 1;
        maze.getMaze()[1][1] = 1;
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(4, solutionPath.size());
    }


    @Test
    void shorterPath() throws Exception {
        mg = new MyMazeGenerator();
        Maze maze = mg.generate(10, 10);

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solutionBEST = best.solve(searchableMaze);
        ArrayList<AState> solutionPathBEST = solutionBEST.getSolutionPath();

        ISearchingAlgorithm dfs = new DepthFirstSearch();
        Solution solutionDFS = dfs.solve(searchableMaze);
        ArrayList<AState> solutionPathDFS = solutionDFS.getSolutionPath();

        assertTrue(solutionPathBEST.size() <= solutionPathDFS.size());
    }


    @Test
    void timeUnderOneMin() throws Exception {
        long start_time,end_time;
        mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        start_time = System.nanoTime();
        Solution solution = best.solve(searchableMaze);
        end_time = System.nanoTime();
        assertTrue(TimeUnit.NANOSECONDS.toMillis(end_time-start_time) < 60000);
    }

    /*
    @Test // fix
    void lowCost() throws Exception {

        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(4, 4);
        maze.getMaze()[1][2] = 1;
        maze.getMaze()[1][3] = 1;
        maze.getMaze()[2][3] = 1;
        maze.getMaze()[2][0] = 1;
        maze.getMaze()[3][0] = 1;
        maze.getMaze()[3][1] = 1;
        //maze.setGoalPosition(new Position(2,4));
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve((ISearchable) searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals("{0,0}", solutionPath.get(0).toString());

    }
    */

}
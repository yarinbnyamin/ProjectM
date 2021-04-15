package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    IMazeGenerator mg;
    ISearchingAlgorithm best = new BestFirstSearch();


    /** in an open maze (no walls) and equal rows and columns BestFirstSearch should return a diagonal line path solution
     * @throws Exception
     */
    @Test
    public void diagonalFullZero() throws Exception {
        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < 100; i++) {
            assertEquals("{" + i + "," + i + "}",solutionPath.get(i).toString());//check if the solution path is a diagonal line
        }
    }

    /** in a closed maze (only walls and no path) the solution is null
     * @throws Exception
     */
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
        assertNull(solution);//the solution should return null because there is no solution path
    }

    /** checks in a specific maze if the path do not go throw a wall for a diagonal shorter path
     * @throws Exception
     */
    @Test
    void noValidDiagonal() throws Exception {
        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(3, 3);
        maze.getMaze()[0][2] = 1;
        maze.getMaze()[1][1] = 1;
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(4, solutionPath.size());//this specific maze should return a ptah of 4 steps
    }


    /**checks that the BestFirstSearch algorithm will return a shorter path or equal to that of the dfs algorithm
     * @throws Exception
     */
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


    /**checks that the BestFirstSearch algorithm finds a solution to a 1000X1000 maze in under 1 min
     * @throws Exception
     */
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


    /**checks that the BestFirstSearch algorithm take the lowest cost path
     * @throws Exception
     */
    @Test
    void lowCost() throws Exception {

        mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(2, 6);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution solution = best.solve(searchableMaze);
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        assertEquals(4+Math.sqrt(2), solutionPath.get(solutionPath.size()-1).getCost());

    }


}
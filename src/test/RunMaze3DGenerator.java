package test;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;

public class RunMaze3DGenerator {
    public static void main(String[] args) throws Exception {
        testMazeGenerator(new MyMaze3DGenerator());
    }
    private static void testMazeGenerator(IMazeGenerator3D mazeGenerator) throws Exception {
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(5,3/*rows*/,5 /*columns*/)));
        Maze3D maze = mazeGenerator.generate(5,3/*rows*/, 5/*columns*/);
        maze.print();
        Position3D startPosition = maze.getStartPosition();
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}

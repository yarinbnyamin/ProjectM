package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;

import java.util.concurrent.TimeUnit;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    public abstract Maze3D generate(int depth, int row, int column);

    public long measureAlgorithmTimeMillis(int depth, int rows, int columns) {
        long start_time,end_time;
        start_time = System.nanoTime();
        generate(depth,rows, columns);
        end_time = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end_time-start_time);
    }
    protected void allNumGenerateor(Maze3D M, int num){ // fill the maze with the num

        for (int i = 0; i < M.getDepth(); i++)
            for (int j = 0; j < M.getRows(); j++)
                for (int k = 0; k < M.getColumns(); k++) {
                    M.getMap()[i][j][k] = num;
                }


    }
}
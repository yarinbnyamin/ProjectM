package algorithms.mazeGenerators;

import java.util.concurrent.TimeUnit;

public abstract class AMazeGenerator implements IMazeGenerator{

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start_time,end_time;
        start_time = System.nanoTime();
        generate(rows, columns);
        end_time = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end_time-start_time);
    }

    protected void allNumGenerateor(Maze M, int num){ // fill the maze with the num

        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getColumns(); j++)
                M.getMaze()[i][j] = num;

    }
}

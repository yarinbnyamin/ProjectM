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
}

package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator{

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long start_time,end_time;
        start_time = System.currentTimeMillis();
        generate(rows, columns);
        end_time = System.currentTimeMillis();
        return end_time-start_time;
    }
}

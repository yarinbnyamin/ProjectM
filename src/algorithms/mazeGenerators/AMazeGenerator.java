package algorithms.mazeGenerators;

import java.util.concurrent.TimeUnit;

public abstract class AMazeGenerator implements IMazeGenerator{

    @Override
    public abstract Maze generate(int rows, int columns) throws Exception;

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) throws Exception {
        long start_time,end_time;
        start_time = System.nanoTime();
        generate(rows, columns);
        end_time = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end_time-start_time);
    }
    /** helping function that sets all the cells of the matrix to a specific value
     * @param M a maze
     * @param num the value being set to every cell in the matrix
     */
    protected void allNumGenerateor(Maze M, int num){ // fill the maze with the num

        for (int i = 0; i < M.getRows(); i++)
            for (int j = 0; j < M.getColumns(); j++)
                M.getMaze()[i][j] = num;

    }
}

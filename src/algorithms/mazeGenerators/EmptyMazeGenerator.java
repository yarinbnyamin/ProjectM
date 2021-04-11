package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) throws Exception {
        Maze m0 = new Maze(rows,columns);
        allNumGenerateor(m0, 0); // start with all zero
        return m0;
    }
}

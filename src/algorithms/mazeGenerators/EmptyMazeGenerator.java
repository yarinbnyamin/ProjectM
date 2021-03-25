package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int columns) {
        Maze m0 = new Maze(rows,columns);
        zeroMaze(m0);
        return m0;
    }

    private void zeroMaze(Maze m0){
        for (int i = 0; i < m0.getRows(); i++) {
            for (int j = 0; j < m0.getColumns(); j++) {
                m0.getMaze()[i][j] = 0;
            }
        }
    }
}

package algorithms.mazeGenerators;

import java.util.Random;


public class SimpleMazeGenerator extends AMazeGenerator{
    private Random rand = new Random();
    @Override
    public Maze generate(int rows, int columns) {
        Maze Sm = new Maze(columns,rows);
        onePathGenerateor(Sm);
        randomlyAddZeros(Sm);
        return Sm;

    }

    private void onePathGenerateor(Maze Sm){
        for (int i = 0; i < Sm.getColumns(); i++)
            Sm.getMaze()[i][0] = 0;

        for (int i = 1; i < Sm.getRows(); i++) {
            int j;
            for ( j = 0; j < Sm.getColumns()-1; j++) {
                Sm.getMaze()[i][j] = 1;
            }
            Sm.getMaze()[i][j] = 0;
        }
    }

    private void randomlyAddZeros(Maze Sm){
        int randNum;
        for (int i = 0; i < Sm.getRows(); i++) {
            for (int j = 0; j < Sm.getColumns(); j++) {
                randNum = rand.nextInt(2);
                if (randNum==0)
                    Sm.getMaze()[i][j] = 0;
            }
        }
    }

}

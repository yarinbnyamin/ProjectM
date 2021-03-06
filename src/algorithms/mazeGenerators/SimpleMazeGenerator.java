package algorithms.mazeGenerators;

import java.util.Random;


public class SimpleMazeGenerator extends AMazeGenerator{
    private Random rand = new Random();
    @Override
    public Maze generate(int rows, int columns) throws Exception {
        Maze Sm = new Maze(rows,columns);
        onePathGenerateor(Sm);
        randomlyAddZeros(Sm);
        return Sm;

    }

    /** generates one random path in the maze
     * @param Sm a maze
     */
    private void onePathGenerateor(Maze Sm){
        int randNum;

        allNumGenerateor(Sm, 1); //generates a maze and fill it with walls only.

        int r=0;
        int c=0;
        while(r != Sm.getGoalPosition().getRowIndex() || c != Sm.getGoalPosition().getColumnIndex()) {
            Sm.getMaze()[r][c] = 0;

            if(c == Sm.getColumns()-1)
                randNum = 0;
            else if (r == Sm.getRows()-1)
                randNum = 1;
            else
                randNum = rand.nextInt(2);

            if(randNum == 0)
                r++;

            if(randNum == 1)
                c++;
        }

    }

    /**randomly Adds Zeros (paths) in the maze
     * @param Sm a maze
     */
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

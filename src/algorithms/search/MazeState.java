package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(AState cameFrom, Position pos) {
        super(cameFrom);
        cost = 1;
        state = pos.toString();
    }


}

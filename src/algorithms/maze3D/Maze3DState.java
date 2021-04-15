package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

import java.util.ArrayList;

public class Maze3DState extends AState {
    private Maze3D maze;
    Position3D pos;
    /*
    public Maze3DState(AState cameFrom, Maze3D maze) {
        super(cameFrom);
        this.maze = maze;
        this.pos = maze.getStartPosition();
        state = pos.toString();
    }
    */
    public Maze3DState(AState cameFrom, Maze3D maze, Position3D pos) {
        super(cameFrom);
        this.maze = maze;
        this.pos = pos;
        state = pos.toString();
    }

    /**
     * @return a list of all the positions that a state can go to.
     */
    public ArrayList<Position3D> getNeighbors() {

        int[][][] m = maze.getMap();
        ArrayList<Position3D> posList = new ArrayList<>();
        int r = pos.getRowIndex(), c = pos.getColumnIndex(), d = pos.getDepthIndex();

        //checks all possible neighbors of a position for a path :
        if(validPos(d,r-1, c) && m[d][r-1][c] == 0)
            posList.add(new Position3D(d,r-1,c));
        if(validPos(d,r, c+1) && m[d][r][c+1] == 0)
            posList.add(new Position3D(d,r,c+1));
        if(validPos(d,r+1, c) && m[d][r+1][c] == 0)
            posList.add(new Position3D(d,r+1,c));
        if(validPos(d,r, c-1) && m[d][r][c-1] == 0)
            posList.add(new Position3D(d,r,c-1));
        if(validPos(d+1,r, c) && m[d+1][r][c] == 0)
            posList.add(new Position3D(d+1,r,c));
        if(validPos(d-1,r, c) && m[d-1][r][c] == 0)
            posList.add(new Position3D(d-1,r,c));
        return  posList;
    }

    /** checks that a positions coordinates are within the maze borders
     * @param d Depth
     * @param r Row
     * @param c Column
     * @return if the position coordinates is within the maze borders
     */
    private Boolean validPos(int d, int r,int c){
        // if the point is in the maze
        return c >= 0 && c < maze.getColumns() && r >= 0 && r < maze.getRows() && d>=0 && d<maze.getDepth();
    }

    public Maze3D getMaze() {
        return maze;
    }

    public void setMaze(Maze3D maze) {
        this.maze = maze;
    }

    public Position3D getPos() {
        return pos;
    }

    public void setPos(Position3D pos) {
        this.pos = pos;
    }
}

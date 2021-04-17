package algorithms.maze3D;


public class Maze3D {
    private int columns;
    private int rows;
    private int depth;
    private Position3D start_position;
    private Position3D end_position;
    private int[][][] maze;

    public Maze3D( int depth, int rows, int columns) throws Exception {

        if (rows < 2 || columns < 2 || depth < 2)
            throw new Exception("Maze size is smaller than 2x2x2");

        this.columns = columns;
        this.rows = rows;
        this.depth = depth;
        this.start_position = new Position3D(0,0,0);
        this.end_position = new Position3D(depth-1,rows-1,columns-1);
        this.maze = new int[depth][rows][columns];
    }
    public void print(){
        boolean found_start= false;
        boolean found_goal= false;
        StringBuilder b = new StringBuilder();
        b.append("{");
        b.append("\n");
        for (int i = 0; i <depth; i++) {
            for (int j = 0; j < rows; j++) {
                b.append("{ ");
                for (int k = 0; k < columns; k++) {
                    if (!found_start){
                        if(start_position.getRowIndex() == j && start_position.getColumnIndex() == k&& start_position.getDepthIndex()==i) {
                            b.append("S ");
                            found_start = true;
                            continue;
                        }
                    }
                    else if(!found_goal){
                        if(end_position.getRowIndex() == j && end_position.getColumnIndex() == k&& end_position.getDepthIndex()==i) {
                            b.append("E ");
                            found_goal = true;
                            continue;
                        }
                    }
                    b.append(maze[i][j][k]+" ");
                }//for k
                b.append("}\n");
            }//for j
            if (i<depth-1) {
                for (int j = 0; j < (columns * 2) + 3; j++)
                    b.append("-");
                b.append("\n");
            }
        }//for i depth
        b.append("}");
        System.out.println(b);
    }

    /** checks that a positions coordinates are within the maze borders
     * @param d Depth
     * @param r Row
     * @param c Column
     * @return if the position coordinates is within the maze borders
     */
    public Boolean validPos(int d, int r,int c){
        // if the point is in the maze
        return c >= 0 && c < columns && r >= 0 && r < rows && d>=0 && d<depth;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Position3D getStartPosition() {
        return start_position;
    }

    public void setStart_position(Position3D start_position) {
        this.start_position = start_position;
    }

    public Position3D getGoalPosition() {
        return end_position;
    }

    public void setGoalPosition(Position3D end_position) {
        this.end_position = end_position;
    }

    public int[][][] getMap() {
        return maze;
    }

    public void setMap(int[][][] maze) {
        this.maze = maze;
    }
}

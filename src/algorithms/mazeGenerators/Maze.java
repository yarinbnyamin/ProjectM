package algorithms.mazeGenerators;

public class Maze {
    private int columns;
    private int rows;
    private Position start_position;
    private Position end_position;
    private int[][] maze;

    public Maze(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        this.maze = new int[rows][columns];
        this.start_position = new Position(0,0);
        this.end_position = new Position(rows-1,columns-1);
    }

    public void print(){
        boolean found_start= false;
        boolean found_goal= false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!found_start){
                    if(start_position.getRowIndex() == i && start_position.getColumnIndex() == j) {
                        System.out.print("S");
                        found_start = true;
                        continue;
                    }
                }
                else if(!found_goal){
                    if(end_position.getRowIndex() == i && end_position.getColumnIndex() == j) {
                        System.out.print("E");
                        found_goal = true;
                        continue;
                    }
                }

                System.out.print(maze[i][j]);

                }
                System.out.println();
            }


    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public Position getStartPosition() {
        return start_position;
    }

    public void setStartPosition(Position start_position) {
        this.start_position = start_position;
    }

    public Position getGoalPosition() {
        return end_position;
    }

    public void setGoalPosition(Position end_position) {
        this.end_position = end_position;
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
}

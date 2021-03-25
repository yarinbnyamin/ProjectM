package algorithms.mazeGenerators;

public class Maze {
    private int columns;
    private int rows;
    private int[][] maze;

    public Maze(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.maze = new int[rows][columns];
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
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

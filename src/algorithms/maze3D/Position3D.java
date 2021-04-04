package algorithms.maze3D;

public class Position3D {
    private int row;
    private int column;
    private int depth;

    public Position3D(int depth, int row, int column) {
        this.row = row;
        this.column = column;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "{" + depth + "," + row + "," + column + '}';
    }

    public int getRowIndex() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumnIndex() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getDepthIndex() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}

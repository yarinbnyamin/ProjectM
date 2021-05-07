package algorithms.mazeGenerators;

import java.io.*;

public class Position implements Serializable {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(row);
        stream.writeObject(column);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        row = (int) stream.readObject();
        column = (int) stream.readObject();
    }

    @Override
    public String toString() {
        return "{" + row + "," + column + '}';
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

}

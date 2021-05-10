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
        // 2 dimensions to byte array
        byte[] b = new byte[4];
        b[0] = (byte)(row / 256);
        b[0] = (byte)(row % 256);
        b[0] = (byte)(column / 256);
        b[0] = (byte)(column % 256);
        stream.writeObject(b);

    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        byte[] b = (byte[])stream.readObject();
        // we add the size and the secondary offset
        row = b[0]*256 + (b[1] & 0xFF);
        column = b[2]*256 + (b[3] & 0xFF);
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

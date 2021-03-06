package algorithms.mazeGenerators;

import java.io.*;

public class Maze implements Serializable {
    private int columns;
    private int rows;
    private Position start_position;
    private Position end_position;
    private int[][] maze;

    public Maze(int rows, int columns) throws Exception {

        if (rows < 2 || columns < 2)
            throw new Exception("Maze size is smaller than 2x2");

        this.columns = columns;
        this.rows = rows;
        this.maze = new int[rows][columns];
        this.start_position = new Position(0,0);
        this.end_position = new Position(rows-1,columns-1);
    }

    public Maze(byte[] info){
        byteToMaze(info);
    }

    public void print(){
        System.out.println(toString());
    }

    /**
     * @param r Row
     * @param c Column
     * @return if the position coordinates is within the maze borders
     */
    public Boolean validPos(int r, int c){
        // if the point is in the maze
        return c >= 0 && c < columns && r >= 0 && r < rows;
    }

    /**
     * transform the maze into byte array
     * @return byte array that represent the maze
     */
    public byte[] toByteArray() {
        //first 12 positions are the demotions and property of the maze
        // we save 2 byte size - the offset of the byte size saved as a secondary mod
        byte[] byteMazeInfo = new byte[12 + rows * columns];
        byteMazeInfo[0] = (byte)(this.rows / 256);
        byteMazeInfo[1] = (byte) (this.rows % 256);
        byteMazeInfo[2] = (byte)(this.columns / 256);
        byteMazeInfo[3] = (byte)(this.columns % 256);
        byteMazeInfo[4] = (byte)(this.start_position.getRowIndex() / 256);
        byteMazeInfo[5] = (byte)(this.start_position.getRowIndex() % 256);
        byteMazeInfo[6] = (byte)(this.start_position.getColumnIndex() / 256);
        byteMazeInfo[7] = (byte)(this.start_position.getColumnIndex() % 256);
        byteMazeInfo[8] = (byte)(this.end_position.getRowIndex() / 256);
        byteMazeInfo[9] = (byte)(this.end_position.getRowIndex() % 256);
        byteMazeInfo[10] = (byte)(this.end_position.getColumnIndex() / 256);
        byteMazeInfo[11] = (byte)(this.end_position.getColumnIndex() % 256);

        //the rest of the maze
        int counter = 12;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                byteMazeInfo[counter] = (byte)getMaze()[i][j];
                counter++;
            }
        }
        return byteMazeInfo;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(toByteArray());
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        byte[] info = (byte[])stream.readObject();
        byteToMaze(info);
    }

    /**
     * this function get and byte array and "extract" the maze from it
     * @param info the byte array we want to get the maze from
     */
    private void byteToMaze(byte[] info){
        //first 12 positions are the demotions and property of the maze
        // we add the size and the secondary offset
        this.rows = info[0]*256 + (info[1] & 0xFF);
        this.columns = info[2]*256 + (info[3] & 0xFF);
        this.maze = new int[rows][columns];
        Position startPos = new Position(info[4] * 256 + (info[5] & 0xFF), info[6] * 256 + (info[7] & 0xFF));
        Position GoalPos = new Position(info[8] * 256 + (info[9] & 0xFF), info[10] * 256 + (info[11] & 0xFF));
        setStartPosition(startPos);
        setGoalPosition(GoalPos);

        //get the rest of the maze
        int CurrentCell = 12;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                maze[row][col]=info[CurrentCell];
                CurrentCell++;
            }
        }

    }

    public String toString() {
        boolean found_start= false;
        boolean found_goal= false;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            b.append("{ ");
            for (int j = 0; j < columns; j++) {
                if (!found_start){
                    if(start_position.getRowIndex() == i && start_position.getColumnIndex() == j) {
                        b.append("S ");
                        found_start = true;
                        continue;
                    }
                }
                else if(!found_goal){
                    if(end_position.getRowIndex() == i && end_position.getColumnIndex() == j) {
                        b.append("E ");
                        found_goal = true;
                        continue;
                    }
                }
                b.append(maze[i][j]+" ");

            }
            b.append("}\n");
        }
        b.delete(b.length()-1,b.length());
        return b.toString();
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

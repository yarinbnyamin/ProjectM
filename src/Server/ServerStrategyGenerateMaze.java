package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeDimensions = (int[]) fromClient.readObject();
            IMazeGenerator generator = new MyMazeGenerator();
            Maze maze = generator.generate(mazeDimensions[0], mazeDimensions[1]);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(out);
            compressorOutputStream.write(maze.toByteArray());
            toClient.writeObject(out.toByteArray());
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

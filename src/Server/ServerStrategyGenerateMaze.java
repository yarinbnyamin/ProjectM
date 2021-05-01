package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] mazeDimensions = (int[]) fromClient.readObject();
            IMazeGenerator generator = new MyMazeGenerator();
            Maze maze = generator.generate(mazeDimensions[0], mazeDimensions[1]);
            SimpleCompressorOutputStream compressorOutputStream = new SimpleCompressorOutputStream(toClient); // change to my
            compressorOutputStream.write(maze.toByteArray());
            //toClient.writeObject(compressorOutputStream);
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

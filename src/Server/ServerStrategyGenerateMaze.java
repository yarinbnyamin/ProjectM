package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy{
    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            // get the maze dimensions from the client
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            int[] mazeDimensions = (int[]) fromClient.readObject();
            //Generate the maze according to the dimensions given
            IMazeGenerator generator = new MyMazeGenerator();
            Maze maze = generator.generate(mazeDimensions[0], mazeDimensions[1]);
            // compress the maze
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(out);
            compressorOutputStream.write(maze.toByteArray());
            // return the compressed the maze generated to the client
            toClient.writeObject(out.toByteArray());
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

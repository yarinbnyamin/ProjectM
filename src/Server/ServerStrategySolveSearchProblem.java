package Server;

import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze) fromClient.readObject();



            ISearchingAlgorithm searchingAlgorithm = new BestFirstSearch();
            ISearchable searchableMaze = new SearchableMaze(maze);
            Solution sol = searchingAlgorithm.solve(searchableMaze);

            //String tempDirectoryPath = System.getProperty("java.io.tmpdir");

            //SimpleCompressorOutputStream compressorOutputStream = new SimpleCompressorOutputStream(toClient); // change to my
            //compressorOutputStream.write(sol);

            toClient.writeObject(sol);
            toClient.flush();

            fromClient.close();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

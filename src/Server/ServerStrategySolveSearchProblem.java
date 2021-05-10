package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    static volatile int index; // number of the saved files
    String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {

            // get the maze from the client
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            byte[] MazeByte = maze.toByteArray();
            boolean eq = false;

            // compress the maze
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MyCompressorOutputStream compress = new MyCompressorOutputStream(out);
            compress.write(MazeByte);

            // check if we got already the same maze
            ObjectInputStream in = null;
            for (int i = 0; i < index && !eq; i++) {
                // check the file
                FileInputStream fi = new FileInputStream(tempDirectoryPath + "\\saved"+i+"Maze.maze");
                in = new ObjectInputStream(fi);
                int row = (int)in.readObject();
                int col = (int)in.readObject();

                // if the maze is with the same size check the rest of the maze
                if(row == maze.getRows() && col == maze.getColumns()) {
                    byte[] savedMaze = (byte[])in.readObject();
                    eq = Arrays.equals(out.toByteArray(), savedMaze); // compare the compressed mazes

                }

            }

            Solution sol;

            if(!eq){ // if we didn't found a maze like that in our data base

                // find solution

                Configurations conf = Configurations.getInstance();
                ISearchingAlgorithm searchingAlgorithm = conf.getMazeSearchingAlgorithm();
                ISearchable searchableMaze = new SearchableMaze(maze);
                sol = searchingAlgorithm.solve(searchableMaze);

                //create file and write to it
                FileOutputStream fileOut = new FileOutputStream(tempDirectoryPath + "\\saved"+index+"Maze.maze");
                ObjectOutputStream o = new ObjectOutputStream(fileOut);
                index++;

                o.writeObject(maze.getRows());
                o.writeObject(maze.getColumns());
                o.writeObject(out.toByteArray());
                o.writeObject(sol);

                o.flush();
                o.close();

            }else{ // we got the maze in our database
                sol = (Solution) in.readObject();
            }

            // return the solution to the client
            toClient.writeObject(sol);
            toClient.flush();

            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

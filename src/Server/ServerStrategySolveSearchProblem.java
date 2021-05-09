package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    static int index = 0;
    String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {

            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            System.out.println(maze);
            byte[] MazeByte = maze.toByteArray();
            boolean eq = false;

            ObjectInputStream in = null;
            for (int i = 0; i < index && !eq; i++) {

                FileInputStream fi = new FileInputStream(tempDirectoryPath + "\\saved"+i+"Maze.maze");
                in = new ObjectInputStream(fi);
                int row = (int)in.readObject();
                int col = (int)in.readObject();

                if(row == maze.getRows() && col == maze.getColumns()) {

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    MyCompressorOutputStream compressorOutputStream = new MyCompressorOutputStream(out);
                    compressorOutputStream.write(MazeByte);
                    byte[] savedMaze = (byte[])in.readObject();
                    eq = Arrays.equals(out.toByteArray(), savedMaze);

                }

            }

            Solution sol;

            if(!eq){
                ISearchingAlgorithm searchingAlgorithm = new BestFirstSearch();
                ISearchable searchableMaze = new SearchableMaze(maze);
                sol = searchingAlgorithm.solve(searchableMaze);
                FileOutputStream fileOut = new FileOutputStream(tempDirectoryPath + "\\saved"+index+"Maze.maze");
                ObjectOutputStream o = new ObjectOutputStream(fileOut);
                index++;

                o.writeObject(maze.getRows());
                o.writeObject(maze.getColumns());

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                MyCompressorOutputStream compress = new MyCompressorOutputStream(out);
                compress.write(maze.toByteArray());
                o.writeObject(out.toByteArray());

                o.writeObject(sol);


                o.flush();
                o.close();

            }else{
                sol = (Solution) in.readObject();
            }

            toClient.writeObject(sol);
            toClient.flush();

            fromClient.close();
            toClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

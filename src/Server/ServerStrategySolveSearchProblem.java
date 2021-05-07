package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy{

    static int index = 0;
    String tempDirectoryPath = System.getProperty("java.io.tmpdir");
    FileInputStream fi;
    ObjectInputStream in;

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {

            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            Maze maze = (Maze) fromClient.readObject();
            byte[] MazeByte = maze.toByteArray();
            boolean eq = false;

            /*
            for (int i = 0; i < index && !eq; i++) {
                //fi = new FileInputStream(new File(tempDirectoryPath + "/saved"+i+"Maze.maze"));
                InputStream targetStream = new FileInputStream(new File(tempDirectoryPath + "/saved"+i+"Maze.maze"));
                in = new ObjectInputStream(targetStream);
                byte[] b = (byte[]) in.readObject();

                if(b[0] == MazeByte[0] && b[1] == MazeByte[1] && b[2] == MazeByte[2] && b[3] == MazeByte[3])
                    eq = Arrays.equals(MazeByte, b);
            }
            */
            Solution sol = null;

            if(!eq){
                ISearchingAlgorithm searchingAlgorithm = new BestFirstSearch();
                ISearchable searchableMaze = new SearchableMaze(maze);
                sol = searchingAlgorithm.solve(searchableMaze);
                OutputStream out = new MyCompressorOutputStream(new FileOutputStream(tempDirectoryPath + "/saved"+index+"Maze.maze"));
                ObjectOutputStream o = new ObjectOutputStream(out);
                index++;
                o.write(maze.toByteArray());
                o.writeObject(sol);
                o.flush();
                o.close();
                out.close();

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

package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool; // Thread pool

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        Configurations conf = Configurations.getInstance();
        this.threadPool = Executors.newFixedThreadPool(conf.getThreadPoolSize());
    }

    public void start(){
        new Thread(this::runServer).start();
    }

    /**
     * the main function of the server
     */
    private void runServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            // reload the last index of maze saved that was in the last time we run this program
            try {
            FileInputStream fi = new FileInputStream(System.getProperty("java.io.tmpdir") + "\\savedIndex.txt");
            ObjectInputStream in = new ObjectInputStream(fi);
            ServerStrategySolveSearchProblem.index = (int)in.readObject();
            } catch (FileNotFoundException f) {
                // if it's the first time we use this program start with index 0
                ServerStrategySolveSearchProblem.index = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }


            while (!stop) {
                try{
                    // enter if we got connection
                    Socket clientSocket = serverSocket.accept();
                    // use the thread pool to "answer" the client
                    threadPool.submit(() -> handleClient(clientSocket));

                } catch (SocketTimeoutException e){
                    // add a logger - in part c
                }
            }

            serverSocket.close();
            threadPool.shutdown();

            // create a file that save the last index of maze save
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("java.io.tmpdir") + "\\savedIndex.txt");
            ObjectOutputStream o = new ObjectOutputStream(fileOut);
            o.writeObject(ServerStrategySolveSearchProblem.index);
            o.flush();
            o.close();

            } catch (IOException e) {
            // add a logger - in part c
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            // add a logger - in part c
        }
    }

    public void stop(){
        stop = true;
    }


}

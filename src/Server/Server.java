package Server;

import java.io.IOException;
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
        this.threadPool = Executors.newFixedThreadPool(3);
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

            while (!stop) {
                try (Socket clientSocket = serverSocket.accept()){ // enter if we got connection
                    // use the thread pool to "answer" the client
                    threadPool.submit(() -> handleClient(clientSocket));

                } catch (SocketTimeoutException e){
                    //
                }
            }
            serverSocket.close();
            threadPool.shutdownNow(); // close all connections
            } catch (IOException e) {
            //
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.ServerStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){
            //
        }
    }

    public void stop(){
        stop = true;
    }


}

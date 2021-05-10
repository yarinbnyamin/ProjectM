package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    private static Configurations conf = null;
    private Properties prop;

    private Configurations() {
        try {
            InputStream input = new FileInputStream("resources/config.properties");
            prop = new Properties();
            prop.load(input);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static Configurations getInstance(){ //singleton
        if(conf == null)
            conf = new Configurations();
        return conf;
    }

    public int getThreadPoolSize(){
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }

    public void setThreadPoolSize(int size){
        prop.setProperty("threadPoolSize", String.valueOf(size));
    }

    public AMazeGenerator getMazeGeneratingAlgorithm(){
        String algo = prop.getProperty("mazeGeneratingAlgorithm");
        switch(algo) {
            case "EmptyMazeGenerator":
                return new EmptyMazeGenerator();
            case "SimpleMazeGenerator":
                return new SimpleMazeGenerator();
        }
        return new MyMazeGenerator();
    }

    public void setMazeGeneratingAlgorithm(String st){
        prop.setProperty("mazeGeneratingAlgorithm", st);
    }

    public ASearchingAlgorithm getMazeSearchingAlgorithm(){
        String algo = prop.getProperty("mazeSearchingAlgorithm");
        switch(algo) {
            case "BreadthFirstSearch":
                return new BreadthFirstSearch();
            case "DepthFirstSearch":
                return new DepthFirstSearch();
        }
        return new BestFirstSearch();
    }

    public void setMazeSearchingAlgorithm(String st){
        prop.setProperty("mazeSearchingAlgorithm", st);
    }

}

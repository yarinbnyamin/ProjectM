package Server;

import java.util.Properties;

public class Configurations {

    private static Properties prop;

    public Configurations() {
        singlton();
    }

    private Properties singlton(){
        if(prop == null)
            prop = new Properties();
        return prop;
    }

    public static int getThreadPoolSize(){
        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }

    public static void setThreadPoolSize(int size){
        prop.setProperty("threadPoolSize", String.valueOf(size));
    }

    public static String getMazeGeneratingAlgorithm(){
        return prop.getProperty("mazeGeneratingAlgorithm");
    }

    public static void setMazeGeneratingAlgorithm(String st){
        prop.setProperty("mazeGeneratingAlgorithm", st);
    }

    public static String getMazeSearchingAlgorithm(){
        return prop.getProperty("mazeSearchingAlgorithm");
    }

    public static void setMazeSearchingAlgorithm(String st){
        prop.setProperty("mazeSearchingAlgorithm", st);
    }

}

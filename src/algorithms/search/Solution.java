package algorithms.search;

import java.io.*;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> solPath;

    public Solution() {
        this.solPath = new ArrayList<>();
    }

    public ArrayList<AState> getSolutionPath() {
        return solPath;
    }

    @Serial
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(solPath);
    }

    @Serial
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        solPath = (ArrayList<AState>) stream.readObject();
    }

    public String toString() {
        return solPath.toString();
    }

    public void setSolPath(ArrayList<AState> solPath) {
        this.solPath = solPath;
    }

    public void addStateToTop(AState state){
        solPath.add(0,state);
    }

    public boolean isSolvable(){
        return solPath == null;
    }
}

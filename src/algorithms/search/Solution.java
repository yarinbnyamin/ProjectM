package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solPath;

    public Solution() {
        this.solPath = new ArrayList<>();
    }

    public ArrayList<AState> getSolutionPath() {
        return solPath;
    }

    public void setSolPath(ArrayList<AState> solPath) {
        this.solPath = solPath;
    }

    public void addStateToTop(AState state){
        solPath.add(0,state);
    }

    public boolean isSolvable(){
        return solPath.size()>0;
    }
}

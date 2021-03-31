package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList solPath;

    public Solution() {
        this.solPath = new ArrayList();
    }

    public ArrayList getSolPath() {
        return solPath;
    }

    public void setSolPath(ArrayList solPath) {
        this.solPath = solPath;
    }

    public void addState(AState state){
        solPath.add(state);
    }

    public boolean isSolvable(){
        return solPath.size()>0;
    }
}

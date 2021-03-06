package algorithms.search;

import java.util.HashMap;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected HashMap<Integer, AState> closeList;
    protected int visitedNodes;
    protected String name;

    public ASearchingAlgorithm() {
        name = "noname";
        visitedNodes = 0;
        closeList = new HashMap<>();
    }

    /**
     * @param goalState the last state of the maze
     * @return back track from the start as Solution
     */
    protected Solution backtrackSol(AState goalState){
        Solution sol=new Solution();
        AState preState =goalState;
        while (preState != null) { // pass all the state in the path
            sol.addStateToTop(preState);
            preState=preState.getCameFrom();
        }
        return sol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }
}

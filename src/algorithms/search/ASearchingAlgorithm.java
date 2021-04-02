package algorithms.search;

import java.util.PriorityQueue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected PriorityQueue<AState> openList;
    protected int visitedNodes;

    public ASearchingAlgorithm() {
        openList = new PriorityQueue<>();
        visitedNodes = 0;
    }

    protected AState popOpenList(){
        visitedNodes++;
        return openList.poll();
    }

    protected Solution backtrackSol(AState goalState){
        Solution sol=new Solution();
        AState preState =goalState;
        while (preState != null) {
            sol.addStateToTop(preState);
            preState=preState.getCameFrom();
        }
        return sol;
    }


    @Override
    public AState search(ISearchable s) {
        return null;
    }

    @Override
    public int getNumberOfVisitedNodes() {
        return 0;
    }
}

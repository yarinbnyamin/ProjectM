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
    public Solution solve(ISearchable s) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    } // add to others
}

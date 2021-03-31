package algorithms.search;

import java.util.PriorityQueue;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected PriorityQueue<AState> openList;
    protected int visitedNodes;

    public ASearchingAlgorithm() {
        openList = new PriorityQueue<AState>();
        visitedNodes = 0;
    }

    protected AState popOpenList(){
        visitedNodes++;
        return openList.poll();
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

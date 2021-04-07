package algorithms.search;


import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> openList;
    protected boolean isDiagonal;

    public BreadthFirstSearch() {
        super();
        name = "BreadthFirstSearch";
        openList = new LinkedList<>();
        isDiagonal=false;
    }

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        openList.add(s.getStartState());
        closeList.put(s.getStartState().hashCode(),s.getStartState());
        while (!openList.isEmpty())
        {
            AState current = openList.poll();
            visitedNodes++;
            if (current.equals(s.getGoalState())){
                sol = current;
                break;
            }

            for (AState neighbor : s.getAllSuccessors(current))
            {
                if(closeList.containsKey(neighbor.hashCode()))
                    continue;
                closeList.put(neighbor.hashCode(),neighbor);
                if(isDiagonal)
                    neighbor.setCost(neighbor.getCost() + Math.sqrt(2) - 1);
                openList.add(neighbor);
            }
        }

        if(sol == null)
            return null;
        return backtrackSol(sol);
    }

}

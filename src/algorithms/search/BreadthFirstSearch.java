package algorithms.search;


import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    protected Queue<AState> openList;

    public BreadthFirstSearch() {
        super();
        name = "BreadthFirstSearch";
        openList = new LinkedList<>();
    }

    /**
     * @param s searchable
     * @return solution of this searchable
     */
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
                openList.add(neighbor);
            }
        }

        if(sol == null) // error + test
            return null;
        return backtrackSol(sol);
    }

}

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
    public Solution solve(ISearchable s) throws Exception {
        AState sol = null;
        openList.add(s.getStartState());
        closeList.put(s.getStartState().hashCode(),s.getStartState());
        while (!openList.isEmpty())
        {
            AState current = openList.poll();
            visitedNodes++;

            // check if got to the end
            if (current.equals(s.getGoalState())){
                sol = current;
                break;
            }

            // get all the possible moves from this state
            for (AState neighbor : s.getAllSuccessors(current))
            {
                if(closeList.containsKey(neighbor.hashCode())) // if all ready was in this state
                    continue;
                closeList.put(neighbor.hashCode(),neighbor);
                openList.add(neighbor);
            }
        }

        if(sol == null) // we pass all the possible moves and didn't got a solution
            throw new Exception("no solution");

        return backtrackSol(sol);
    }

}

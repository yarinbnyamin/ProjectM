package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    private Stack<AState> openList;

    public DepthFirstSearch() {
        super();
        name = "DepthFirstSearch";
        openList = new Stack<>();
    }

    /**
     * @param s searchable
     * @return solution of this searchable
     */
    @Override
    public Solution solve(ISearchable s) throws Exception {
        AState sol = null;
        openList.push(s.getStartState());
        closeList.put(openList.peek().hashCode(),openList.peek());
        while (!openList.isEmpty())
        {
            AState current = openList.pop();
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
                openList.push(neighbor);
            }
        }

        if(sol == null) // we pass all the possible moves and didn't got a solution
            throw new Exception("no solution to" + s);

        return backtrackSol(sol);
    }

}

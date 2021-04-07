package algorithms.search;

import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    private Stack<AState> openList;

    public DepthFirstSearch() {
        super();
        name = "DepthFirstSearch";
        openList = new Stack<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        openList.push(s.getStartState());
        closeList.put(openList.peek().hashCode(),openList.peek());
        while (!openList.isEmpty())
        {
            AState current = openList.pop();
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
                openList.push(neighbor);
            }
        }

        if(sol == null)
            return null;
        return backtrackSol(sol);
    }

}

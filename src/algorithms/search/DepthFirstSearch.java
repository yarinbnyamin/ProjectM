package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{

    private Stack<AState> stack;
    private HashMap<Integer, AState> visited;

    public DepthFirstSearch() {
        super();
        name = "DepthFirstSearch";
        visited = new HashMap<>();
        stack = new Stack<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        stack.push(s.getStartState());

        while (!stack.isEmpty())
        {
            AState current = stack.pop();
            visitedNodes++;
            if (current.equals(s.getGoalState())){
                sol = current;
                break;
            }
            visited.put(current.hashCode(),current);

            for (AState neighbor : s.getAllSuccessors(current))
            {
                if(visited.containsKey(neighbor.hashCode()))
                    continue;

                stack.push(neighbor);
            }
        }

        if(sol == null)
            return null;
        return backtrackSol(sol);
    }

}

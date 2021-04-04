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
        visited.put(stack.peek().hashCode(),stack.peek());
        while (!stack.isEmpty())
        {
            AState current = stack.pop();
            if (current.equals(s.getGoalState())){
                sol = current;
                break;
            }
            visitedNodes++;

            for (AState neighbor : s.getAllSuccessors(current))
            {
                if(visited.containsKey(neighbor.hashCode()))
                    continue;
                visited.put(neighbor.hashCode(),neighbor);
                stack.push(neighbor);
            }
        }

        if(sol == null)
            return null;
        return backtrackSol(sol);
    }

}

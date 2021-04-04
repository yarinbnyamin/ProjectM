package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    private Queue<AState> queue;
    private HashMap<Integer, AState> visited;

    public BreadthFirstSearch() {
        super();
        name = "BreadthFirstSearch";
        visited = new HashMap<>();
        queue = new LinkedList<>();
    }

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        queue.add(s.getStartState());

        while (!queue.isEmpty())
        {
            AState current = queue.poll();
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

                queue.add(neighbor);
            }
        }

        if(sol == null)
            return null;
        return backtrackSol(sol);
    }

}

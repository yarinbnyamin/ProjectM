package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    protected Queue<AState> queue;
    protected HashMap<Integer, AState> visited;
    protected boolean isDiagonal;

    public BreadthFirstSearch() {
        super();
        name = "BreadthFirstSearch";
        visited = new HashMap<>();
        queue = new LinkedList<>();
        isDiagonal=false;
        //queue = new PriorityQueue<AState>();
    }

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        queue.add(s.getStartState());
        visited.put(s.getStartState().hashCode(),s.getStartState());
        while (!queue.isEmpty())
        {
            AState current = queue.poll();
            visitedNodes++;
            if (current.equals(s.getGoalState())){
                sol = current;
                break;
            }


            for (AState neighbor : s.getAllSuccessors(current))
            {
                if(visited.containsKey(neighbor.hashCode()))
                    continue;
                visited.put(neighbor.hashCode(),neighbor);
                if(isDiagonal)
                    neighbor.setCost(neighbor.getCost() + Math.sqrt(2) - 1);
                queue.add(neighbor);
            }
        }

        if(sol == null)
            return null;
        return backtrackSol(sol);
    }

}

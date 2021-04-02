package algorithms.search;

import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    private Queue<AState> queue;

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        queue.add(s.getStartState());
        while (!queue.isEmpty())
        {
            AState current = queue.poll();

            for (AState neighbor : s.getAllSuccessors(current))
            {
                if (!neighbor.state.equals(s.getGoalState()))
                {
                    neighbor.setState("grey"); // delete ?
                    queue.add(neighbor);
                }
                else
                    sol = neighbor;
            }
        }

        return backtrackSol(sol);
    }

}

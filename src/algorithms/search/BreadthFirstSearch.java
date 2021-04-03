package algorithms.search;

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    private Queue<AState> queue;

    @Override
    public Solution solve(ISearchable s) {
        AState sol = null;
        queue = new LinkedList<>();
        queue.add(s.getStartState());


        while (!queue.isEmpty())
        {
            AState current = queue.poll();
            if (current.equals(s.getGoalState())){
                sol = current;
                break;
            }

            if(current.getState().equals(State.grey))
                current.setState(State.black);
            else
                current.setState(State.grey);




            for (AState neighbor : s.getAllSuccessors(current))
            {

                //neighbor.setState("grey");
                if(!(neighbor.getState().equals(State.black) || neighbor.getState().equals(State.grey))) {
                    neighbor.setState(State.grey);
                    queue.add(neighbor);
                }

                //queue.add(neighbor);
            }

        }

        return backtrackSol(sol);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}

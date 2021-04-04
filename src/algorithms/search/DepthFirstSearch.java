package algorithms.search;

import java.util.LinkedList;

public class DepthFirstSearch extends ASearchingAlgorithm{

    public DepthFirstSearch() {
        super();
        name = "DepthFirstSearch";
    }

    @Override
    public Solution solve(ISearchable domain) {
        return null;
    }

    /*
    private LinkedList<AState> neighborsLst;
    @Override
    public Solution solve(ISearchable domain) {
        AState start= domain.getStartState();
        AState goal= domain.getGoalState();
        AState sol=dfsRec(start,goal,domain);
        return backtrackSol(sol);
    }
    public AState dfsRec(AState current,AState goal,ISearchable domain){
        current.setState(State.grey);
        if (current.equals(goal))
            return current;
        for (AState neighbor : domain.getAllSuccessors(current)) {
            if (neighbor.getState().equals(State.white)){
                if (dfsRec(neighbor,goal,domain).equals(goal))
                    return neighbor;
            }
        }
        current.setState(State.black);
        return null;
    }
    @Override
    public String getName() {
        return super.getName();
    }

     */
}

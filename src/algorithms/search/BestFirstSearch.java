package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
        name = "BestFirstSearch";
        openList = new PriorityQueue<>();
        isDiagonal=true;
    }

    public Solution solve(ISearchable s) {
        //s.setDiagonal(true);
        return super.solve(s);
    }
}

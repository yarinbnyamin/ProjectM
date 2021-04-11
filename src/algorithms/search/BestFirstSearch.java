package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
        name = "BestFirstSearch";
        openList = new PriorityQueue<>();
    }

    public Solution solve(ISearchable s) {
        return super.solve(s);
    }
}

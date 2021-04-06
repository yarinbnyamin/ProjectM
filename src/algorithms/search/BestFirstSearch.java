package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
        name = "BestFirstSearch";
        queue = new PriorityQueue<>();
        isDiagonal=true;
    }

    public Solution solve(ISearchable s) {
        //s.setDiagonal(true);
        return super.solve(s);
    }
}

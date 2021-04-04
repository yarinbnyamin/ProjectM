package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
        name = "BestFirstSearch";
        //queue = new LinkedList<>();
    }

    public Solution solve(ISearchable s) {
        //s.setDiagonal(true);
        return super.solve(s);
    }
}

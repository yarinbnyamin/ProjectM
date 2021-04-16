package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {
        super();
        name = "BestFirstSearch";
        openList = new PriorityQueue<>();
    }

}

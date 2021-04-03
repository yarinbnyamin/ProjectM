package algorithms.search;

public class BestFirstSearch extends BreadthFirstSearch{

    public BestFirstSearch() {

    }
    /*
    @Override
    public AState search(ISearchable s) {
        return super.search(s);
    }
    */
    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}

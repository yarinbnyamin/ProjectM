package algorithms.search;

public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain) throws Exception;
    int getNumberOfNodesEvaluated();
    String getName();
}

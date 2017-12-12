/**
 * interface for algorithms
 */
public interface ISearcher {

    /**
     * function that runs the algorithm
     * @param searchable the graph we want to search
     * @return the path for start state to goal state
     */
    Solution Search (ISearchable searchable);

}

public interface ISearcher {

    ISolution Search (ISearchable searchable);

    int GetNumberOfNodesEvaluated();

}

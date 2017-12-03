import java.util.List;

public interface ISearchable<T> {

    State<T> GetInitialState();


    State<T> GetGoalState();


    List<State<T>> GetAllPossibleStates(State<T> s);


    Solution<T> GetSolution();
}

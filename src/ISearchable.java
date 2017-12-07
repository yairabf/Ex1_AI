import java.util.List;

public interface ISearchable<T> {

    State<T> getInitialState();


    State<T> getGoalState();


    List<State<T>> getAllPossibleStates(State<T> s, int time);


    Solution<T> getSolution();
}

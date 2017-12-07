import java.util.List;

public interface ISearchable<T> {

    State<T> getInitialState();


    State<T> getGoalState();

    void setGoalState(State<T> s);

    List<State<T>> getAllPossibleStates(State<T> s, int time);

    int getSize();

    Solution<T> getSolution();
}

import java.util.List;

/**
 *  Interface for searchable graph
 */
public interface ISearchable {

    /**
     * getter
     * @return the start state of the graph
     */
    State getInitialState();


    /**
     * setter
     * @param s the state we want to set as the goal state
     */
    void setGoalState(State s);

    /**
     * this function receives all the possible neighbors of a state.
     * @param s the state wwe wants his neighbors
     * @param time the creation time of the neighbors
     * @return list of neighbors
     */
    List<State> getAllPossibleStates(State s, int time);

    /**
     * getter
     * @return the size of the graph
     */
    int getSize();


}

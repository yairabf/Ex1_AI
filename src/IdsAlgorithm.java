import java.util.List;

/**
 * This class implements the IDSalgorithmm
 */
public class IdsAlgorithm implements ISearcher{
    /**
     * The function that runs the algorithm
     * @param searchable the graph we searching
     * @return the path to goal from start state.
     */
    @Override
    public Solution Search(ISearchable searchable) {
        /*for each node we looking for the goal in the i depth*/
        for(int i = 0; i < searchable.getSize() * searchable.getSize(); i++) {
            State found = dls(searchable.getInitialState(), i, searchable,0);
            if (found != null) {
                return new Solution(found);
            }
        }
        return null;
    }


    /**
     *
     * @param state the state we want to start from it go down in the depth to look for the goal
     * @param depth the current depth
     * @param searchable the map
     * @param clock unnecessary param for this algorithm
     * @return the goal state
     */
    private State dls(State state, int depth, ISearchable searchable, int clock) {
        /* If we found the goal state in the highest depth*/
        if (state.getState().equals("G") && depth == 0){
            return state;
        }
        if (depth > 0){
            //receives all the possible states
            List<State> neighbors = searchable.getAllPossibleStates(state,0);
            for (State child:neighbors) {
                /*if the current child isn't the father of the current state*/
                if(state.getCameFrom() == null || !state.getCameFrom().getPoint().equals(child.getPoint())) {
                    child.setCameFrom(state);
                    /*call the function with the child*/
                    State found = dls(child, depth - 1, searchable, 0);
                    if (found != null) {
                        return found;
                    }
                }
            }
        }
        return null;
    }


}

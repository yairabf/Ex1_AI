import java.util.List;
import java.util.PriorityQueue;

/**
 * this class is implementing the A* algorithm
 */
public class AStarAlgorithm implements ISearcher {
    private int number_of_state_evaluated = 0;
    @Override
    public ISolution Search(ISearchable searchable) {
        int clock = 0;
        StateComparator comparator = new StateComparator();
        //open list
        PriorityQueue<State> open_list = new PriorityQueue<>(comparator);
        searchable.getInitialState().setCreation_time(clock);
        clock++;
        open_list.add(searchable.getInitialState());


         //while the list isn't empty or we haven't reach the maximum calculation of states we allowed
        while (!open_list.isEmpty() && this.number_of_state_evaluated < Math.pow(searchable.getSize(),2)){
            State current_state = open_list.poll();
            this.number_of_state_evaluated++;

            //find the goal state
            if(current_state.getState().equals("G")){
                return new Solution(current_state);
            } else {
                List<State> neighbors = searchable.getAllPossibleStates(current_state,clock);
                for (State s: neighbors) {

                    //if the neighbor isn't in the open list
                    if (!duplicatePruning(open_list,s)) {

                        //if the neighbor isn't the father of the current child
                        if(current_state.getCameFrom() == null || !current_state.getCameFrom().getPoint().equals(s.getPoint())) {
                            s.setCost(current_state.getCost() + s.getCost());
                            s.setCameFrom(current_state);
                            open_list.add(s);
                        }
                    }
                }
                clock++;
            }
        }
        return null;
    }

    /**
     *
     * @param list the open list
     * @param state the state we want to check
     * @return if the state is in the current list or not.
     */
    private boolean duplicatePruning(PriorityQueue<State> list,State state){
        for (State s:list) {
            if (s.getPoint().equals(state.getPoint()))
                return true;
        }
        return false;
    }
}

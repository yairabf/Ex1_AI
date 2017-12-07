import java.util.List;
import java.util.PriorityQueue;

public class AStarAlgorithm implements ISearcher {

    @Override
    public ISolution Search(ISearchable searchable) {
        int clock = 0;
        StateComparator comparator = new StateComparator();
        PriorityQueue<State> open_list = new PriorityQueue<>(comparator);
        searchable.getInitialState().setCreation_time(clock);
        clock++;
        open_list.add(searchable.getInitialState());
        while (!open_list.isEmpty()){
            State current_state = open_list.poll();
            if(current_state.getState().equals("G")){
                searchable.setGoalState(current_state);
                return new Solution<String>();
            } else {
                List<State> neighbors = searchable.getAllPossibleStates(current_state,clock);
                for (State s: neighbors) {

                    if (!open_list.contains(s)) {
                        s.setCost(current_state.getCost() + s.getCost());
                        s.setCameFrom(current_state);
                        open_list.add(s);
                    }
                }
            }
        }
        return null;
    }

}

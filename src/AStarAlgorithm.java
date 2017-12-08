import java.util.List;
import java.util.PriorityQueue;

public class AStarAlgorithm implements ISearcher {
    int number_of_state_evalauated = 0;
    @Override
    public ISolution Search(ISearchable searchable) {
        int clock = 0;
        StateComparator comparator = new StateComparator();
        PriorityQueue<State> open_list = new PriorityQueue<>(comparator);
        searchable.getInitialState().setCreation_time(clock);
        clock++;
        open_list.add(searchable.getInitialState());
        while (!open_list.isEmpty() && this.number_of_state_evalauated < Math.pow(searchable.getSize(),2)){
            State current_state = open_list.poll();
            this.number_of_state_evalauated++;
            if(current_state.getState().equals("G")){
                searchable.setGoalState(current_state);
                return new Solution<String>();
            } else {
                List<State> neighbors = searchable.getAllPossibleStates(current_state,clock);
                for (State s: neighbors) {

                    if (!duplicatePruning(open_list,s)) {
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
    private boolean duplicatePruning(PriorityQueue<State> list,State state){
        for (State s:list) {
            if (s.getPoint().equals(state.getPoint()))
                return true;
        }
        return false;
    }
}

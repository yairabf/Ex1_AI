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
                return new Solution<String>();
            } else {

            }
        }
        return null;
    }

    @Override
    public int GetNumberOfNodesEvaluated() {
        return 0;
    }
}

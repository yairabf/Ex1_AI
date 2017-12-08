import java.util.List;

public class IdsAlgorithm implements ISearcher{
    @Override
    public ISolution Search(ISearchable searchable) {
        int clock = 0;
        for(int i = 0; i < searchable.getSize() * searchable.getSize(); i++) {
            State found = dls(searchable.getInitialState(), i, searchable,clock);
            clock++;
            if (found != null) {
                searchable.setGoalState(found);
                return new Solution<>();
            }
        }
        return null;
    }


    private State dls(State state, int depth, ISearchable searchable, int clock) {
        if (depth == 0 && state.equals("G")){
            return state;
        }
        if (depth > 0){
            List<State> neighbors = searchable.getAllPossibleStates(state,clock);
            for (State child:neighbors) {
                child.setCameFrom(state);
                State found = dls(child, depth - 1, searchable, clock + 1);
                if (found != null){
                    return found;
                }
            }
        }
        return null;
    }


}

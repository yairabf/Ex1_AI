import java.util.Comparator;

/**
 * Comparator that set the priority of the state in the open list in A* algorithm
 */
public class StateComparator implements Comparator<State>{
    @Override
    public int compare(State o1, State o2) {
        double s_one_value = o1.getHeuristics() + o1.getCost();
        double s_two_value = o2.getHeuristics() + o2.getCost();
        /*if the f(n) = Heuristics + cost are equal*/
        if(s_one_value - s_two_value < 0.0001 && s_one_value - s_two_value >= 0){
            /*compare the creation time*/
            if(o1.getCreation_time() < o2.getCreation_time()){
                return -1;
            } else if(o1.getCreation_time() > o2.getCreation_time()) {
                return 1;
                //compare priority
            } else {
                return Integer.compare(o2.getPriority(),o1.getPriority());
            }
        } else if(s_one_value < s_two_value){
            return -1;
        } else {
            return 1;
        }
    }

}

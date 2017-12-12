
public class Solution {
    private State goal_state;

    /**
     * Constructor.
     * @param goal_state the
     */
    public Solution(State goal_state) {
        this.goal_state = goal_state;
    }

    /**
     * The function gets a goal state and build the path to him.
     * @return the path to the goal state.
     */
    public String buildSolution() {
        State current = this.goal_state;
        String route = "";
        int cost = current.getCost();
        if(current.getCost() == 0){
            cost = calculate_cost(current);
        }
        while (current.getCameFrom() != null){
            int i = current.getCameFrom().getPoint().x;
            int j = current.getCameFrom().getPoint().y;
            if(current.getPoint().x < i){
                if(current.getPoint().y < j){
                    route = "-LU".concat(route);
                } else if (current.getPoint().y > j){
                    route = "-RU".concat(route);
                } else {
                    route = "U".concat(route);
                }
            } else if (current.getPoint().x > i) {
                if(current.getPoint().y < j){
                    route = "-LD".concat(route);
                } else if (current.getPoint().y > j){
                    route = "-RD".concat(route);
                } else {
                    route = "-D".concat(route);
                }
            } else {
                if(current.getPoint().y < j){
                    route = "-L" .concat(route);
                } else {
                    route = "-R" .concat(route);
                }
            }
            current = current.getCameFrom();
        }
        route = route.substring(1);
        String str_cost = Integer.toString(cost);
        route = route + " " + str_cost;
        return route;
    }

    /**
     * calculate the cost of the path
     * @param s state
     * @return the cost
     */
    private int calculate_cost(State s) {
        int cost = 0;
        State count = s;
        while (count.getCameFrom() != null){
            cost = cost + count.getCameFrom().getCost();
            count = count.getCameFrom();
        }
        return cost;
    }
}

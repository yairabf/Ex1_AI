
public class Solution implements ISolution{
    State goal_state;

    public Solution(State goal_state) {
        this.goal_state = goal_state;
    }

    @Override
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
                    route = "-LU" + route;
                } else if (current.getPoint().y > j){
                    route = "-RU" + route;
                } else {
                    route = "U" + route;
                }
            } else if (current.getPoint().x > i) {
                if(current.getPoint().y < j){
                    route = "-LD" + route;
                } else if (current.getPoint().y > j){
                    route = "-RD" + route;
                } else {
                    route = "-D" + route;
                }
            } else {
                if(current.getPoint().y < j){
                    route = "-L" + route;
                } else {
                    route = "-R" + route;
                }
            }
            current = current.getCameFrom();
        }
        route = route.substring(1);
        String str_cost = Integer.toString(cost);
        route = route + " " + str_cost;
        return route;
    }

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


public class Solution<T> implements ISolution{
    @Override
    public String buildSolution(State s) {
        State current = s;
        String route = "";
        int cost = current.getCost();
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
        route = str_cost + " " + route;
        return route;
    }
}

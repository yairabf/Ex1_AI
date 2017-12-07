import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StringMap implements ISearchable{

    private char[][] map;
    private State<String> initial_state;
    private State<String> goal_state;
    private int size;

    public void setInitial_state(State<String> initial_state) {
        this.initial_state = initial_state;
    }

    public void setGoal_state(State<String> goal_state) {
        this.goal_state = goal_state;
    }

    public StringMap(int size, char[][] m) {
        this.size = size;
        this.map = m;
    }


    @Override
    public State<String> getInitialState() {
        return this.initial_state;
    }

    @Override
    public State<String> getGoalState() {
        return this.goal_state;
    }

    @Override
    public List<State<String>> getAllPossibleStates(State s, int time) {
        List<State<String>> neighbors = new ArrayList<>();
        for (int i = s.getPoint().x - 1; i <= s.getPoint().x + 1; i++){
            for (int j = s.getPoint().y - 1; j <= s.getPoint().y + 1; j++){
                State<String> child = createChild(i,j, s, time);
                if(child != null){
                    child.setHeuristics(evaluateHeuristics(child.getPoint(), this.goal_state.getPoint()));
                    neighbors.add(child);
                }
            }
        }
        return neighbors;
    }

    private State<String> createChild(int i, int j, State s, int time) {

        // checks if the next step is in the limit of the map and it is not water.
        if((i >= 0 && j >= 0) && (i < this.size && j < this.size) &&(map[i][j] != 'W') &&
                (i != s.getPoint().x || j != s.getPoint().y)){

            //checks the Diagonals
            if(i != s.getPoint().x && j != s.getPoint().y){

                //left diagonal
                if(j < s.getPoint().y){
                    if(i < s.getPoint().x) {
                        if ((map[i][j - 1] != 'W') && (map[i - 1][j] != 'W')){
                            String value= "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j), evaluatePriority(i,j,s));
                        }
                    } else {
                        if ((map[i][j - 1] != 'W') && (map[i + 1][j] != 'W')){
                            String value= "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j),evaluatePriority(i,j,s));
                        }
                    }

                    //right diagonal
                } else {
                    if(i < s.getPoint().x) {
                        if ((map[i][j + 1] != 'W') && (map[i - 1][j] != 'W')){
                            String value= "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j),evaluatePriority(i,j,s));
                        }
                    } else {
                        if ((map[i][j + 1] != 'W') && (map[i + 1][j] != 'W')) {
                            String value = "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i, j), evaluatePriority(i, j, s));
                        }
                    }
                }
            } else {
                String value = "" + map[i][j];
                return new State<>(value, evaluateCost(map[i][j]), time,
                        new Point(i, j), evaluatePriority(i, j, s));
            }
        }
        return null;
    }

    @Override
    public Solution getSolution() {
        return null;
    }

    private int evaluateCost(char val){
        switch (val){
            case 'R':
                return 1;
            case 'D':
                return 3;
            case 'H':
                return 10;
            default:
                return 1;
        }
    }

    private double evaluateHeuristics(Point point, Point goal_point){
        return Math.hypot(point.x - goal_point.x, point.y - goal_point.y);

    }

    private int evaluatePriority(int i, int j, State s){
        if (i < s.getPoint().x){
            if(j > s.getPoint().y)
                return 1;
            else if(j < s.getPoint().y)
                return 3;
            else
                return 2;
        } else if (i > s.getPoint().x) {
            if (j > s.getPoint().y)
                return 7;
            else if (j < s.getPoint().y)
                return 5;
            else return 6;
        } else {
            if (j > s.getPoint().y)
                return 8;
            else
                return 4;
        }
    }
}

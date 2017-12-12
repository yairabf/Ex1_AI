import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*8

 */
public class StringMap implements ISearchable{

    //map n x n  of chars
    private char[][] map;
    // the start state
    private State initial_state;
    // the goal state
    private State goal_state;
    // size of the map
    private int size;
    // the algorithm we solve the route with
    private int algorithm;

    /**
     * setter
     * @param initial_state the start state
     */
    private void setInitial_state(State initial_state) {
        this.initial_state = initial_state;
    }

    /**
     * setter
     * @param goal_state the goal state
     */
    public void setGoalState(State goal_state) {
        this.goal_state = goal_state;
    }

    /**
     * the path to the file that describe the map
     * @param file_path as a string
     */
    public StringMap(String file_path) {
        this.buildMap(file_path);
    }

    /**
     * build map by reading the file
     * @param file_path as string
     */
    private void buildMap(String file_path) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path))) {
            this.algorithm = typeOfAlg(bufferedReader.readLine());
            this.size = Integer.parseInt(bufferedReader.readLine());
            this.map = new char[this.size][this.size];
            String currentLine;
            int line = 0;
            while ((currentLine = bufferedReader.readLine())!=null){
                for (int i = 0; i < currentLine.length(); i ++){
                    map[line][i] = currentLine.charAt(i);
                    if(currentLine.charAt(i) == 'S'){
                        String val = "" + 'S';
                        this.setInitial_state(new State(val,0,0,new Point(line,i),0));
                    }
                    if(currentLine.charAt(i) == 'G'){
                        String val = "" + 'G';
                        this.setGoalState(new State(val,0,0,new Point(line,i),0));
                    }
                }
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getter
     * @return algorithm
     */
    public int getAlgorithm() {
        return algorithm;
    }

    /**
     * set the type of the algorithm
     * @param s string that represent the algorithm
     * @return 1 for IDS 2 for A*
     */
    private int typeOfAlg(String s) {
        if(s.equals("IDS"))
            return 1;
        else

            return 2;
    }

    /**
     * getter
     * @return initial state
     */
    @Override
    public State getInitialState() {
        return this.initial_state;
    }

    /**
     * this function receives all the possible neighbors of a state.
     * @param s the state wwe wants his neighbors
     * @param time the creation time of the neighbors
     * @return list of neighbors
     */
    @Override
    public List<State> getAllPossibleStates(State s, int time) {
        List<State> neighbors = new ArrayList<>();
        for (int i = s.getPoint().x - 1; i <= s.getPoint().x + 1; i++){
            for (int j = s.getPoint().y - 1; j <= s.getPoint().y + 1; j++){
                State child = createChild(i,j, s, time);
                if(child != null){
                    child.setHeuristics(evaluateHeuristics(child.getPoint(), this.goal_state.getPoint()));
                    neighbors.add(child);
                }
            }
        }
        return neighbors;
    }

    /**
     * getter
     * @return the size of the graph
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     *
     * @param i the i index
     * @param j the j index
     * @param s the state
     * @param time creation time
     * @return child of the current state.
     */
    private State createChild(int i, int j, State s, int time) {

        // checks if the next step is in the limit of the map and it is not water.
        if((i >= 0 && j >= 0) && (i < this.size && j < this.size) && (map[i][j] != 'W') &&
                (i != s.getPoint().x || j != s.getPoint().y)){

            //checks the Diagonals
            if(i != s.getPoint().x && j != s.getPoint().y){

                //left diagonal
                if(j < s.getPoint().y){
                    if(i < s.getPoint().x) {
                        /*check if the places next to the location aren't water*/
                        if ((map[i][j + 1] != 'W') && (map[i + 1][j] != 'W')){
                            String value= "" + map[i][j];
                            return new State(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j), evaluatePriority(i,j,s));
                        }
                    } else {
                        /*check if the places next to the location aren't water*/
                        if ((map[i - 1][j] != 'W') && (map[i][j + 1] != 'W')){
                            String value= "" + map[i][j];
                            return new State(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j),evaluatePriority(i,j,s));
                        }
                    }

                    //right diagonal
                } else {
                    if(i < s.getPoint().x) {
                        /*check if the places next to the location aren't water*/
                        if ((map[i + 1][j] != 'W') && (map[i][j - 1] != 'W')){
                            String value= "" + map[i][j];
                            return new State(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j),evaluatePriority(i,j,s));
                        }
                    } else {
                        /*check if the places next to the location aren't water*/
                        if ((map[i - 1][j] != 'W') && (map[i][j - 1] != 'W')) {
                            String value = "" + map[i][j];
                            return new State(value, evaluateCost(map[i][j]), time,
                                    new Point(i, j), evaluatePriority(i, j, s));
                        }
                    }
                }
            } else {
                String value = "" + map[i][j];
                return new State(value, evaluateCost(map[i][j]), time,
                        new Point(i, j), evaluatePriority(i, j, s));
            }
        }
        return null;
    }

    /**
     * evaluate the cost according to the string
     * @param val the value
     * @return the cost
     */
    private int evaluateCost(char val){
        switch (val){
            case 'R':
                return 1;
            case 'D':
                return 3;
            case 'H':
                return 10;
            default:
                return 0;
        }
    }

    /**
     * evaluate the Heuristics
     * @param point the current state position
     * @param goal_point the goal position
     * @return the Heuristics
     */
    private double evaluateHeuristics(Point point, Point goal_point){
        return Math.max(Math.abs(point.x - goal_point.x), Math.abs(point.y - goal_point.y));

    }

    /**
     * this function evaluate the Priority
     * @param i the i index
     * @param j the j index
     * @param s the state
     * @return Priority
     */
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

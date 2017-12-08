import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringMap implements ISearchable{

    private char[][] map;
    private State<String> initial_state;
    private State<String> goal_state;
    private int size;
    private int algorithm;

    public void setInitial_state(State<String> initial_state) {
        this.initial_state = initial_state;
    }

    public void setGoalState(State goal_state) {
        this.goal_state = goal_state;
    }

    public StringMap(String file_path) {
        this.buildMap(file_path);

    }

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

    public int getAlgorithm() {
        return algorithm;
    }

    private int typeOfAlg(String s) {
        if(s.equals("IDS"))
            return 1;
        else

            return 2;
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

    @Override
    public int getSize() {
        return this.size;
    }

    private State<String> createChild(int i, int j, State s, int time) {

        // checks if the next step is in the limit of the map and it is not water.
        if((i >= 0 && j >= 0) && (i < this.size && j < this.size) && (map[i][j] != 'W') &&
                (i != s.getPoint().x || j != s.getPoint().y)){

            //checks the Diagonals
            if(i != s.getPoint().x && j != s.getPoint().y){

                //left diagonal
                if(j < s.getPoint().y){
                    if(i < s.getPoint().x) {
                        if ((map[i][j + 1] != 'W') && (map[i + 1][j] != 'W')){
                            String value= "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j), evaluatePriority(i,j,s));
                        }
                    } else {
                        if ((map[i - 1][j] != 'W') && (map[i][j + 1] != 'W')){
                            String value= "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j),evaluatePriority(i,j,s));
                        }
                    }

                    //right diagonal
                } else {
                    if(i < s.getPoint().x) {
                        if ((map[i + 1][j] != 'W') && (map[i][j - 1] != 'W')){
                            String value= "" + map[i][j];
                            return new State<>(value, evaluateCost(map[i][j]), time,
                                    new Point(i,j),evaluatePriority(i,j,s));
                        }
                    } else {
                        if ((map[i - 1][j] != 'W') && (map[i][j - 1] != 'W')) {
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
                return 0;
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

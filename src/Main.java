import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

    private static final String FILENAME = "src/input.txt";

    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            String algorithm = bufferedReader.readLine();
            String size = bufferedReader.readLine();
            int mapSize = Integer.parseInt(size);
            State start = new State("S"), end = new State("G");
            char [][] map = new char[mapSize][mapSize];
            String currentLine;
            int line = 0;
            while ((currentLine = bufferedReader.readLine())!=null){
                for (int i = 0; i < currentLine.length(); i ++){
                    map[line][i] = currentLine.charAt(i);
                    if(currentLine.charAt(i) == 'S'){
                        String val = "" + 'S';
                        start = new State(val,0,0,new Point(line,i),10);
                    }
                    if(currentLine.charAt(i) == 'G'){
                        String val = "" + 'G';
                        end = new State(val,0,0,new Point(line,i),10);
                    }
                }
                line++;
            }
            StringMap stringMap = new StringMap(mapSize,map);
            stringMap.setInitial_state(start);
            stringMap.setGoal_state(end);
            AStarAlgorithm aStarAlgorithm = new AStarAlgorithm();
            ISolution s = aStarAlgorithm.Search(stringMap);
            s.buildSolution(stringMap.getGoalState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



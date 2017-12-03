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
            Vector<Vector<State<String>>> map = new Vector<>();
            int mapSize = Integer.parseInt(size);
            String currentLine;
            while ((currentLine = bufferedReader.readLine())!=null){
                Vector<State<String>> row = new Vector<>(mapSize);
                for (char c: currentLine.toCharArray()) {
                    String s = "" + c;
                    State<String> currentState = new State(s);
                    currentState.SetCost(calculateCost(c));
                    row.add(currentState);
                }
                map.add(row);
            }
            printMap(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculateCost(char c){
        int cost = 0;
        switch (c){
            case 'R':
                cost = 1;
                break;
            case 'D':
                cost = 3;
                break;
            case 'H':
                cost = 10;
                break;
            default:
                break;
        }
        return cost;
    }

    private static void printMap(Vector<Vector<State<String>>> map){
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < map.size(); j++){
                System.out.print(map.elementAt(i).elementAt(j).GetState() + " ");
            }
            System.out.println("\n");
        }
    }
}



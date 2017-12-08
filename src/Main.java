import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final String INPUT_FILENAME = "input.txt";

    private static final String OUTPUT_FILENAME = "output.txt";


    public static void main(String[] args) {
        StringMap stringMap = new StringMap(INPUT_FILENAME);
        String route = "no path";

        //if algorithm is IDS
        if (stringMap.getAlgorithm() == 1) {
            IdsAlgorithm idsAlgorithm = new IdsAlgorithm();
            ISolution s = idsAlgorithm.Search(stringMap);
            if (s != null)
                route = s.buildSolution();

            //if algorithm is A*
        } else {
            AStarAlgorithm aStarAlgorithm = new AStarAlgorithm();
            ISolution s = aStarAlgorithm.Search(stringMap);
            if (s != null)
                route = s.buildSolution();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILENAME))) {

            bw.write(route);

            System.out.println(route);

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


}



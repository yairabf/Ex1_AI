import java.awt.*;

import java.util.Vector;

public class Main {

    private static final String FILENAME = "src/input.txt";

    public static void main(String[] args) {
            StringMap stringMap = new StringMap(FILENAME);
            if(stringMap.getAlgorithm() == 1){
                IdsAlgorithm idsAlgorithm = new IdsAlgorithm();
                ISolution s = idsAlgorithm.Search(stringMap);
                s.buildSolution(stringMap.getGoalState());
            } else {
                AStarAlgorithm aStarAlgorithm = new AStarAlgorithm();
                ISolution s = aStarAlgorithm.Search(stringMap);
                s.buildSolution(stringMap.getGoalState());
            }
    }


}



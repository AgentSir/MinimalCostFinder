import entities.Path;
import entities.PathFinder;
import entities.Reader;
import entities.TestCase;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        FinderData finderData = new FinderData();                   //uncommented the line if you want use the test input data
//        for (entities.TestCase testCase : reader.getTestCases()) {  //uncommented the line if you want use the test input data
        Reader reader = new Reader();                                 //comment out this line if you want use the test input data
        for (TestCase testCase : reader.getTestCases()) {             //comment out this line if you want use the test input data
            runTestCase(testCase.getCities(), testCase.getSourceDestination());
        }
    }

    //run all the tests for find the minimal cost of transportation between two cities
    private static void runTestCase(Map<String, List<Path>> cities, Map<String, String> sourceDestination) {
        PathFinder pathFinder = new PathFinder(cities);

        for(Map.Entry<String, String> entry : sourceDestination.entrySet()) {
            String source = entry.getKey();
            String destination = entry.getValue();
            int transportationCost = pathFinder.findPathCost(source, destination);
            System.out.println(transportationCost);
        }
    }
}

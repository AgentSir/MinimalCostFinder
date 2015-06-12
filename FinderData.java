import entities.Path;
import entities.TestCase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Here stored the test data for test the program
 */
public class FinderData {
    private List<TestCase> testCases;
    private Map<String, List<Path>> cities;
    private List<Path> paths;
    private Map<String, String> sourceDestination;
    private Path path;
    private TestCase testCase;

    public FinderData() {
        testCases = new ArrayList<>();
        cities = new LinkedHashMap<>();
        paths = new ArrayList();
        sourceDestination = new LinkedHashMap<>();

        path = new Path(2, 1);
        paths.add(path);
        path = new Path(3, 3);
        paths.add(path);
        cities.put("gdansk", paths);

        path = new Path(1, 1);
        paths = new ArrayList();
        paths.add(path);
        path = new Path(3, 1);
        paths.add(path);
        path = new Path(4, 4);
        paths.add(path);
        cities.put("bydgoszcz", paths);

        path = new Path(1, 3);
        paths = new ArrayList();
        paths.add(path);
        path = new Path(2, 1);
        paths.add(path);
        path = new Path(4, 1);
        paths.add(path);
        cities.put("torun", paths);

        path = new Path(2, 4);
        paths = new ArrayList();
        paths.add(path);
        path = new Path(3, 1);
        paths.add(path);
        cities.put("warszawa", paths);

        sourceDestination.put("gdansk", "warszawa");
        sourceDestination.put("bydgoszcz", "warszawa");

        testCase = new TestCase();
        testCase.setCities(cities);
        testCase.setSourceDestination(sourceDestination);
        testCases.add(testCase);
    }
}

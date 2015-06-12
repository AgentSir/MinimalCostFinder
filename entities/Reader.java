package entities;

import entities.Path;
import entities.TestCase;
import exceptions.LimitException;
import java.util.*; // for Scanner, Maps and Lists

/*
 * entities.Reader is used for read the input data from keyboard.
 */

public class Reader {

    private Scanner scanner = new Scanner(System.in);

    private List<TestCase> testCases;
    private Map<String, List<Path>> cities;
    private List<Path> paths;
    private Map<String, String> sourceDestination;

    public Reader() {
        testCases = new ArrayList<>();
        cities = new LinkedHashMap<>();
        paths = new ArrayList();
        sourceDestination = new LinkedHashMap<>();

        // read and write the data from keyboard
        try {
            // read the number of tests (not more 10)
            int countTests = scanner.nextInt();

            if (countTests <= 10) {
                for (int i = 0; i < countTests; i++) {
                    // read the number of cities (not more 10000)
                    int countCities = scanner.nextInt();

                    if (countCities <= 10000) {
                        for (int j = 0; j < countCities; j++){
                            // read the name of city and the numbers of neighbors of this city
                            String cityName = scanner.next();
                            int countNeighbours = scanner.nextInt();
                            paths = new ArrayList();
                            // read the id and transportation cost for all neighbors
                            for (int k = 0; k < countNeighbours; k++) {
                                paths.add(k, new Path(scanner.nextInt(), scanner.nextInt()));
                            }
                            cities.put(cityName, paths);
                        }
                    } else {
                        throw new LimitException("Exceeding the limit of number of cities");
                    }

                    TestCase testCase = new TestCase();
                    testCase.setCities(cities);
                    // read the number of searches
                    int countSearch = scanner.nextInt();

                    // read the city name source and city name destination
                    for (int j = 0; j < countSearch; j++){
                        String source = scanner.next();
                        String destination = scanner.next();
                        sourceDestination.put(source, destination);
                    }
                    testCase.setSourceDestination(sourceDestination);
                    testCases.add(testCase);

                    // empty line separating the tests
                    System.out.println();
                }
            }else {
                throw new LimitException("Exceeding the limit of number of tests");
            }

        } catch (LimitException ex){
            System.out.println(ex);
        } catch (NumberFormatException ex) {
            System.out.println("Entered incorrect data");
        }
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

}

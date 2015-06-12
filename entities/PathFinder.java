package entities;

import entities.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Realise the search a paths with minimal transportation cost from city to city
 */

public class PathFinder {

    private int [][] costsMatrix;
    private int [][] minimalCostsMatrix;
    private ArrayList<String> citiesNames;

    public PathFinder(Map<String, List<Path>> cities) {
        costsMatrix = new int[cities.size()][cities.size()];
        minimalCostsMatrix = new int [cities.size()][cities.size()];
        initCitiesNames(cities);
        generateAdjacencyMatrix(cities);
        calculateMinimalCosts(cities);
    }

    public int findPathCost(String source, String destination) {
        int sourceId = citiesNames.indexOf(source);
        int destinationId = citiesNames.indexOf(destination);
        return minimalCostsMatrix[sourceId][destinationId];
    }

    private void initCitiesNames(Map<String, List<Path>> cities) {
        citiesNames = new ArrayList<>(cities.keySet());
    }

    /** Method calculate the minimal cost for all possible paths,
     * and write costs to matrix wish minimal costs of paths.
     * For implementation task used Dijkstra's algorithm.
     *
     *@param cities: The input data
     */
    private void calculateMinimalCosts(Map<String, List<Path>> cities) {

        int bestCost;
        boolean [] mark = new boolean[cities.size()]; // array of labels
        int[] cost = new int[cities.size()]; // array of distances

        for (Map.Entry<String, List<Path>> entry : cities.entrySet()) {
            int sourceCity = citiesNames.indexOf(entry.getKey());
            fill(mark, cost, 200001);
            cost[sourceCity] = 0;

            while (true) {
                bestCost = 200001;
                int u = -1;
                for (int i = 0; i < citiesNames.size(); i++) {
                    if (!mark[i] && cost[i] < bestCost) {
                        u = i;
                        bestCost = cost[i];
                    }
                }

                if (bestCost == 200001) break;

                for (int i = 0; i < citiesNames.size(); i++) {
                    if (!mark[i] && costsMatrix[u][i] != -1) {
                        if (cost[i] > cost[u] + costsMatrix[u][i]) {
                            cost[i] = cost[u] + costsMatrix[u][i];
                        }
                    }
                }

                mark[u] = true;
                // write minimal costs to minimalCostsMatrix
                for (int i = 0; i < cities.size(); i++) {
                    minimalCostsMatrix[sourceCity][i]=cost[i];
                }
            }
        }
    }

    //Method for preset max cost for all cities
    private void fill (boolean [] used, int [] dist, int maxCost) {
        for (int i = 0; i < dist.length; i++) {
            dist[i] = maxCost;
            used[i] = false;
        }
    }

    //Method for generate adjacency matrix from input data
    private void generateAdjacencyMatrix (Map<String, List<Path>> cities) {
        for (Map.Entry<String, List<Path>> entry : cities.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                costsMatrix[citiesNames.indexOf(entry.getKey())][entry.getValue().get(i).getCityId() - 1] = entry.getValue().get(i).getCost();
            }
        }
        //if two cities have no path set up cost == -1
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j <cities.size(); j++) {
                if (costsMatrix[i][j] == 0) costsMatrix[i][j] = -1;
            }
        }
    }
}

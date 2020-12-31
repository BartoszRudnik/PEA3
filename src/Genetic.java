import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genetic {

    UtilsFunction utils = new UtilsFunction();

    private List<int[]> population;

    public Genetic(){

    }

    public List<int[]> getPopulation() {
        return population;
    }

    public void setPopulation(List<int[]> population) {
        this.population = population;
    }

    public void generatePopulation(int [][] graph, int numberOfVertex, int populationSize, int exclusivity){

        utils.setNumberOfVertex(numberOfVertex);
        population = new ArrayList<>();
        Random random = new Random();

        int routeCost;

        int [] route = new int[numberOfVertex + 2];
        int [] newRoute = utils.greedy(graph);
        routeCost = utils.getRouteCost(graph, newRoute);

        System.arraycopy(newRoute, 0, route, 0, newRoute.length);
        route[route.length - 1] = routeCost;

        population.add(route);

        int rGreedySize = (populationSize / 5) * 4;

        for(int i = 0; i < rGreedySize; i++){

            int n = random.nextInt(numberOfVertex - 2) + 1;

            route = new int[numberOfVertex + 2];
            newRoute = utils.randomGreedy(graph, n);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            population.add(route);

        }

        int randomSize = populationSize - rGreedySize - 1;

        for(int i = 0; i < randomSize; i++){

            route = new int[numberOfVertex + 2];
            newRoute = utils.shuffleArray(newRoute);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            population.add(route);

        }

    }

}

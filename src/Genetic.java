import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Genetic {

    UtilsFunction utils = new UtilsFunction();

    private List<int[]> population;

    public Genetic(){

        population = new ArrayList<>();

    }

    public List<int[]> getPopulation() {
        return population;
    }

    public void setPopulation(List<int[]> population) {
        this.population = population;
    }

    public void generatePopulation(int [][] graph, int numberOfVertex, int populationSize, int exclusivity){

        utils.setNumberOfVertex(numberOfVertex);
        Random random = new Random();

        int routeCost;

        int [] route = new int[numberOfVertex + 2];
        int [] newRoute = utils.greedy(graph);
        routeCost = utils.getRouteCost(graph, newRoute);

        System.arraycopy(newRoute, 0, route, 0, newRoute.length);
        route[route.length - 1] = routeCost;

        population.add(route);

        int rGreedySize = ((populationSize - exclusivity) / 5) * 4;

        for(int i = 0; i < rGreedySize; i++){

            int n = random.nextInt(numberOfVertex - 2) + 1;

            route = new int[numberOfVertex + 2];
            newRoute = utils.randomGreedy(graph, n);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            population.add(route);

        }

        int randomSize = populationSize - exclusivity - rGreedySize - 1;

        for(int i = 0; i < randomSize; i++){

            route = new int[numberOfVertex + 2];
            newRoute = utils.shuffleArray(newRoute);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            population.add(route);

        }

    }

    public void sortPopulation(List<int []> toSort){
        toSort.sort(Comparator.comparingInt(o -> o[o.length - 1]));
    }

    public void clearPopulation(int populationSize, int exclusivity){

        sortPopulation(population);

        if (populationSize > exclusivity) {
            population.subList(exclusivity, populationSize).clear();
        }

    }

    public int [] tournamentSelection(int numberOfVertex, int populationSize, int n){

        Random random = new Random();

        boolean [] test = new boolean[populationSize];
        int [] bestRoute = new int[numberOfVertex + 2];
        int bestCost = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){

            int randomIndex = random.nextInt(populationSize);

            if(test[randomIndex]) {
                i--;
                continue;
            }
            else
                test[randomIndex] = true;

            int [] currentRoute = population.get(randomIndex);

            if(currentRoute[currentRoute.length - 1] < bestCost){

                bestCost = currentRoute[currentRoute.length - 1];
                bestRoute = currentRoute.clone();

            }

        }

        return bestRoute;

    }

    public int [] rankingSelection(int populationSize){

        Random random = new Random();

        List<int []> tmpPopulation = new ArrayList<>(population);
        double [] fitnessValue = new double[populationSize];
        double totalFitnessValue  = 0.0;
        double fitnessSum = 0.0;
        int returnIndex = 0;

        sortPopulation(tmpPopulation);

        for(int i = 0; i < populationSize; i++){

            fitnessValue[i] = (populationSize - i) / (double) populationSize * (populationSize - 1);

        }

        for(int i = 0; i < populationSize; i++)
            totalFitnessValue += fitnessValue[i];

        int helpRank = populationSize;

        for(int i = 0; i < populationSize; i++){

            fitnessValue[i] = helpRank / totalFitnessValue;
            helpRank--;

        }

        double testValue = random.nextDouble();

        for(int i = 0; i < populationSize; i++){

            fitnessSum += fitnessValue[i];

            if(fitnessSum >= testValue){

                returnIndex = i;
                break;

            }

        }

        return  tmpPopulation.get(returnIndex);

    }

    public int [] rouletteSelection(int populationSize){

        Random random = new Random();

        List<int []> tmpPopulation = new ArrayList<>(population);
        double [] fitnessValue = new double[populationSize];
        double totalFitnessValue  = 0.0;
        double fitnessSum = 0.0;
        int returnIndex = 0;

        sortPopulation(tmpPopulation);

        for(int i = 0; i < populationSize; i++){

            int [] route = tmpPopulation.get(i);
            int pathCost = route[route.length - 1];

            fitnessValue[i] = 1 / (double) pathCost;

        }

        for(int i = 0; i < populationSize; i++)
            totalFitnessValue += fitnessValue[i];

        for(int i = 0; i < populationSize; i++)
            fitnessValue[i] /= totalFitnessValue;

        double testValue = random.nextDouble();

        for(int i = 0; i < populationSize; i++){

            fitnessSum += fitnessValue[i];

            if(fitnessSum >= testValue){

                returnIndex = i;
                break;

            }

        }

        return  tmpPopulation.get(returnIndex);

    }

}

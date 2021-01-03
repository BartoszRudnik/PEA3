import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Genetic {

    UtilsFunction utils = new UtilsFunction();

    private List<int[]> population;

    public Genetic() {

        population = new ArrayList<>();

    }

    public List<int[]> getPopulation() {
        return population;
    }

    public void setPopulation(List<int[]> population) {
        this.population = population;
    }

    public void generatePopulation(int[][] graph, int numberOfVertex, int populationSize) {

        utils.setNumberOfVertex(numberOfVertex);
        Random random = new Random();

        int routeCost;

        int[] route = new int[numberOfVertex + 2];
        int[] newRoute = utils.greedy(graph);
        routeCost = utils.getRouteCost(graph, newRoute);

        System.arraycopy(newRoute, 0, route, 0, newRoute.length);
        route[route.length - 1] = routeCost;

        population.add(route);

        int rGreedySize = (populationSize / 5) * 4;

        for (int i = 0; i < rGreedySize; i++) {

            int n = random.nextInt(numberOfVertex - 2) + 1;

            route = new int[numberOfVertex + 2];
            newRoute = utils.randomGreedy(graph, n);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            if (utils.checkRoute(route))
                population.add(route);
            else
                i--;

        }

        int randomSize = populationSize - rGreedySize - 1;

        for (int i = 0; i < randomSize; i++) {

            route = new int[numberOfVertex + 2];
            newRoute = utils.shuffleArray(newRoute);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            if (utils.checkRoute(route))
                population.add(route);
            else
                i--;

        }

    }

    private void sortPopulation(List<int[]> toSort) {
        toSort.sort(Comparator.comparingInt(o -> o[o.length - 1]));
    }

    public void clearPopulation(int populationSize, int exclusivity) {

        sortPopulation(population);

        if (populationSize > exclusivity) {
            population.subList(exclusivity, populationSize - 1).clear();
        }

    }

    public void algorithm(int[][] graph, int numberOfVertex, int seconds, int populationSize, int exclusivity, int selectionType, int crossoverType, double mutationChance, int mutationType) {

        Random random = new Random();
        Crossover crossover = new Crossover(numberOfVertex);
        Mutation mutation = new Mutation();
        Selection selection = new Selection(population);

        long finishTime = System.currentTimeMillis() + seconds * 1000L;

        int[] bestRoute = new int[numberOfVertex + 2];
        bestRoute[bestRoute.length - 1] = Integer.MAX_VALUE;

        generatePopulation(graph, numberOfVertex, populationSize);

        for (int[] route : population) {

            if (route[route.length - 1] < bestRoute[bestRoute.length - 1]) {

                bestRoute = route.clone();

            }

        }

        while (System.currentTimeMillis() < finishTime) {

            int[] firstParent;
            int[] secondParent;

            List<int[]> newPopulation = new ArrayList<>();

            for (int i = 0; i < (populationSize - exclusivity) / 2; i++) {

                int[] child1;
                int[] child2;

                if (selectionType == 0) {

                    firstParent = selection.tournament(numberOfVertex, population.size(), 2);
                    secondParent = selection.tournament(numberOfVertex, population.size(), 2);

                } else if (selectionType == 1) {

                    firstParent = selection.roulette(population.size());
                    secondParent = selection.roulette(population.size());

                } else {

                    firstParent = selection.ranking(population.size());
                    secondParent = selection.ranking(population.size());

                }

                if (crossoverType == 0) {

                    child1 = crossover.twoPoint(graph, firstParent, secondParent, numberOfVertex);
                    child2 = crossover.twoPoint(graph, secondParent, firstParent, numberOfVertex);

                } else if (crossoverType == 1) {

                    child1 = crossover.order(graph, firstParent, secondParent, numberOfVertex);
                    child2 = crossover.order(graph, secondParent, firstParent, numberOfVertex);

                } else if (crossoverType == 2) {

                    child1 = crossover.partiallyMapped(graph, firstParent, secondParent, numberOfVertex);
                    child2 = crossover.partiallyMapped(graph, secondParent, firstParent, numberOfVertex);

                } else if (crossoverType == 3) {

                    child1 = crossover.cycle(graph, firstParent, secondParent, numberOfVertex);
                    child2 = crossover.cycle(graph, secondParent, firstParent, numberOfVertex);

                } else if (crossoverType == 4) {

                    int[][] children = crossover.cycle2(graph, firstParent, secondParent, numberOfVertex);

                    child1 = children[0];
                    child2 = children[1];

                } else if (crossoverType == 5) {

                    child1 = crossover.sequentialConstructive(graph, firstParent, secondParent, numberOfVertex);
                    child2 = crossover.sequentialConstructive(graph, secondParent, firstParent, numberOfVertex);

                } else {

                    child1 = crossover.enhancedSequentialConstructive(graph, firstParent, secondParent, numberOfVertex);
                    child2 = crossover.enhancedSequentialConstructive(graph, secondParent, firstParent, numberOfVertex);

                }

                newPopulation.add(child1);
                newPopulation.add(child2);

            }

            for (int[] route : newPopulation) {

                double chance = random.nextDouble();

                if (mutationChance >= chance) {

                    int start = 0;
                    int end = 0;

                    while (start == end) {
                        start = random.nextInt(numberOfVertex - 2) + 1;
                        end = random.nextInt(numberOfVertex - 2) + 1;
                    }

                    if (mutationType == 0)
                        route = mutation.insertRoute(route, start, end);
                    if (mutationType == 1)
                        route = mutation.swapRoute(route, start, end);
                    if (mutationType == 2)
                        route = mutation.reverseRoute(route, start, end);

                    route[route.length - 1] = utils.getRouteCost(graph, route);

                }

            }

            clearPopulation(populationSize, exclusivity);

            population.addAll(newPopulation);

            for (int[] route : population) {

                if (route[route.length - 1] < bestRoute[bestRoute.length - 1]) {

                    bestRoute = route.clone();

                }

            }

        }

        for (int i : bestRoute)
            System.out.print(i + " ");

    }

}

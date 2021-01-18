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

    //poczatkowa populacja sklada z jednego osobika wygenerowanego algorytmem zachlannym
    //80% osbnikow wygenerowanych jest algorytmem zachlanno-losowym
    //pozostale osobniki generowane sa w sposob losowy
    public void generatePopulation(int[][] graph, int numberOfVertex, int populationSize) {

        utils.setNumberOfVertex(numberOfVertex);
        Random random = new Random();

        int[] route = new int[numberOfVertex + 2];
        int[] newRoute = utils.greedy(graph);
        int routeCost = utils.getRouteCost(graph, newRoute);

        System.arraycopy(newRoute, 0, route, 0, newRoute.length);
        route[route.length - 1] = routeCost;

        population.add(route);

        int rGreedySize = (populationSize / 5) * 4;

        for (int i = 0; i < rGreedySize; i++) {

            int n = random.nextInt(numberOfVertex - 2) + 1;

            route = new int[numberOfVertex + 2];
            newRoute = utils.randomGreedy(graph, n);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = utils.getRouteCost(graph, route);

            if (utils.checkRoute(route))
                population.add(route);
            else {
                i--;
            }

        }

        int randomSize = populationSize - rGreedySize - 1;

        newRoute = new int[numberOfVertex];

        for (int i = 0; i < numberOfVertex; i++)
            newRoute[i] = i;

        for (int i = 0; i < randomSize; i++) {

            route = new int[numberOfVertex + 2];
            newRoute = utils.shuffleArray(newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = utils.getRouteCost(graph, route);

            if (utils.checkRoute(route))
                population.add(route);
            else
                i--;

        }

    }

    //funkcja do sortowania osobnikow wedlug ich kosztu przejscia, od osobnika o najmniejszym koszcie przejscie do osobnika
    //o najwiekszym koszcie przejscia
    private void sortPopulation(List<int[]> toSort) {
        toSort.sort(Comparator.comparingInt(o -> o[o.length - 1]));
    }

    //funkcja usuwa osobniki z populacji zostwiajac w niej jedynie k najlepszych osobnikow (elitaryzm)
    public void clearPopulation(int populationSize, int exclusivity) {

        sortPopulation(population);

        if (populationSize > exclusivity) {
            population.subList(exclusivity, population.size()).clear();
        }

    }

    public int[] algorithm(int[][] graph, int numberOfVertex, int seconds, int populationSize, int exclusivity, int selectionType, int crossoverType, double mutationChance, int mutationType, int memeticType, double crossoverChance, boolean memetic) {

        Random random = new Random();
        Crossover crossover = new Crossover(numberOfVertex);
        Mutation mutation = new Mutation(numberOfVertex);
        Selection selection = new Selection(population);

        int[] bestRoute = new int[numberOfVertex + 2];
        bestRoute[bestRoute.length - 1] = Integer.MAX_VALUE;

        //generowanie poczatkowej populacji
        generatePopulation(graph, numberOfVertex, populationSize);

        //czas wykonywania sie algorytmu
        long finishTime = System.currentTimeMillis() + seconds * 1000L;

        //wyznaczanie najlepszego do tej pory rozwiazania
        for (int[] route : population) {

            if (route[route.length - 1] < bestRoute[bestRoute.length - 1]) {

                bestRoute = route.clone();

            }

        }

        while (System.currentTimeMillis() < finishTime) {

            int[] firstParent;
            int[] secondParent;

            List<int[]> newPopulation = new ArrayList<>();

            //dopoki nie stworzona zostanie odpowiednio liczna nowa populacja
            while (newPopulation.size() < (populationSize - exclusivity)) {

                //losowana jest wartosc sluzaca do weryfikacji szansy krzyzowania
                double chance = random.nextDouble();

                int[] child1;
                int[] child2;

                //w zaleznosci od wybranego algorytmu selekcjonowani sa rodzice
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

                //jesli ustawiony przez nas parametr szansy na krzyzowanie jest wiekszy rowny niz wartosc wylosowana
                //w aktualnej iteracji wykonywane jest krzyzowanie
                if (crossoverChance >= chance) {

                    //krzyzowanie w zaleznosci od wybranego algorytmu
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

                    } else if (crossoverType == 6) {

                        child1 = crossover.enhancedSequentialConstructive(graph, firstParent, secondParent, numberOfVertex);
                        child2 = crossover.enhancedSequentialConstructive(graph, secondParent, firstParent, numberOfVertex);

                    } else {

                        child1 = crossover.singlePoint(graph, firstParent, secondParent, numberOfVertex);
                        child2 = crossover.singlePoint(graph, secondParent, firstParent, numberOfVertex);

                    }

                    //dodawanie stworzonych dzieci do nowej populacji
                    newPopulation.add(child1);
                    newPopulation.add(child2);

                }

            }

            //mutowanie stworzonych dzieci
            for (int[] route : newPopulation) {

                //losowana jest wartosc sluzaca do weryfikacji wspolczynnika mutacji
                double chance = random.nextDouble();

                //jesli ustawiony przez nas wspolczynnik mutacji jest wiekszy rowny niz wylosowana wartosc
                //to zachodzi mutacja danego osobnika
                if (mutationChance >= chance) {

                    int start = 0;
                    int end = 0;

                    while (start == end) {
                        start = random.nextInt(numberOfVertex - 2) + 1;
                        end = random.nextInt(numberOfVertex - 2) + 1;
                    }

                    //mutacja w zaleznosci od wybranego algorytmu
                    if (mutationType == 0)
                        route = mutation.insertRoute(route, start, end);

                    if (mutationType == 1)
                        route = mutation.swapRoute(route, start, end);

                    if (mutationType == 2)
                        route = mutation.reverseRoute(route, start, end);

                    route[route.length - 1] = utils.getRouteCost(graph, route);

                }

            }

            //w zaleznosci od ustawionego parametru moze zostac wykonany algorytm memetyczny
            if (memetic) {

                //dla kazdego osobnika z nowej populacji wyszukujemy w jego sasiedztwie najlepsza mozliwa rotacja
                for (int[] route : newPopulation) {

                    //odczytujemy wyznaczone najlepsze parametry
                    int[] parameters = mutation.bestRoute(graph, route, numberOfVertex, memeticType);

                    //dokonujemy transformacji wedlug wyznaczonych parametrów
                    if (memeticType == 0)
                        route = mutation.insertRoute(route, parameters[0], parameters[1]);
                    else if (memeticType == 1)
                        route = mutation.swapRoute(route, parameters[0], parameters[1]);
                    else
                        route = mutation.reverseRoute(route, parameters[0], parameters[1]);

                    route[route.length - 1] = utils.getRouteCost(graph, route);

                }

            }

            //czyscimy stara populacji uwzgledniajac kryterium elitaryzmu
            clearPopulation(population.size(), exclusivity);

            //dodajemy osobnikow z nowej populacji
            population.addAll(newPopulation);

            //wykonujemy ewentualna aktualizacje najlepszego dotychczasowego rozwiazania
            for (int[] route : population) {

                if (route[route.length - 1] < bestRoute[bestRoute.length - 1]) {

                    bestRoute = route.clone();

                }

            }

        }

        //wypisujemy najlepsze znalezione rozwiazanie
        for (int i : bestRoute)
            System.out.print(i + " ");

        return bestRoute;

    }

}

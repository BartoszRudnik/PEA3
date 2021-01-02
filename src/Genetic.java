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

    public void generatePopulation(int [][] graph, int numberOfVertex, int populationSize){

        utils.setNumberOfVertex(numberOfVertex);
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

            if(utils.checkRoute(route))
                population.add(route);
            else
                i--;

        }

        int randomSize = populationSize - rGreedySize - 1;

        for(int i = 0; i < randomSize; i++){

            route = new int[numberOfVertex + 2];
            newRoute = utils.shuffleArray(newRoute);
            routeCost = utils.getRouteCost(graph, newRoute);

            System.arraycopy(newRoute, 0, route, 0, newRoute.length);
            route[route.length - 1] = routeCost;

            if(utils.checkRoute(route))
                population.add(route);
            else
                i--;

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

    public int [] twoPointCrossover(int [][] graph, int [] firstParent, int [] secondParent, int numberOfVertex){

        int [] child = new int[firstParent.length];

        Random random = new Random();
        int i = 0;
        int j = 0;

        while(i == j) {

            i = random.nextInt(numberOfVertex - 2) + 1;
            j = random.nextInt(numberOfVertex - 2) + 1;

        }

        if(j > i){

            int tmp = i;
            i = j;
            j = tmp;

        }

        int [] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for(int start = j; start <= i; start++) {

            chosenVertexes[tmpIndex] = firstParent[start];
            tmpIndex++;

        }

        tmpIndex = 0;

        for(int start = 0; start < firstParent.length - 2; start++){

            boolean add = true;
            int tmp = secondParent[start];

            for (int chosenVertex : chosenVertexes)
                if (tmp == chosenVertex) {
                    add = false;
                    break;
                }

            if(add) {
                child[tmpIndex] = tmp;
                tmpIndex++;
            }

        }

        int tmpIndex2 = chosenVertexes.length - 1;

        for(int start = tmpIndex; start < firstParent.length - 2; start++){

            child[start] = chosenVertexes[tmpIndex2];
            tmpIndex2--;

        }

        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int [] orderCrossover(int [][] graph, int [] firstParent, int [] secondParent, int numberOfVertex){

        int [] child = new int[numberOfVertex + 2];

        Random random = new Random();
        int i = 0;
        int j = 0;

        while(i == j) {

            i = random.nextInt(numberOfVertex - 2) + 1;
            j = random.nextInt(numberOfVertex - 2) + 1;

        }

        if(j > i){

            int tmp = i;
            i = j;
            j = tmp;

        }

        int [] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for(int start = j; start <= i; start++) {

            chosenVertexes[tmpIndex] = firstParent[start];
            tmpIndex++;

        }

        tmpIndex = i + 1;
        int tmpIndex2 = i + 1;

        for(int k = 0; k < secondParent.length; k++){

            int tmpVertex = secondParent[tmpIndex2];
            boolean test = true;

            for (int chosenVertex : chosenVertexes) {

                if (tmpVertex == chosenVertex) {
                    test = false;
                    break;
                }

            }

            if(test) {
                child[tmpIndex] = tmpVertex;
                tmpIndex++;
            }

            tmpIndex2++;

            if(tmpIndex == secondParent.length - 2)
                tmpIndex = 1;

            if(tmpIndex2 == secondParent.length - 2)
                tmpIndex2 = 1;

        }

        tmpIndex2 = 0;

        for(int k = j; k <= i; k++) {

            child[k] = chosenVertexes[tmpIndex2];
            tmpIndex2++;

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int [] partiallyMappedCrossover(int [][] graph, int [] firstParent, int [] secondParent, int numberOfVertex){

        int [] child = new int[numberOfVertex + 2];

        Random random = new Random();
        int i = 0;
        int j = 0;

        while(i == j) {

            i = random.nextInt(numberOfVertex - 2) + 1;
            j = random.nextInt(numberOfVertex - 2) + 1;

        }

        if(j > i){

            int tmp = i;
            i = j;
            j = tmp;

        }

        int [] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for(int k = j;  k <= i; k++){

            chosenVertexes[tmpIndex] = secondParent[k];
            child[k] = secondParent[k];
            tmpIndex++;

        }

        for(int k = 1; k < numberOfVertex; k++){

            boolean test = true;

            for(int chosenVertex : chosenVertexes){

                if(firstParent[k] == chosenVertex) {

                    test = false;
                    break;

                }

            }

            if(test && (k < j || k > i))
                child[k] = firstParent[k];


        }

        for(int k = 1; k < numberOfVertex; k++){

            boolean test = true;

            for(int v : child){

                if(k == v){

                    test = false;
                    break;

                }

            }

            if(test){

                for(int h = 1; h < numberOfVertex; h++) {

                    if (child[h] == 0) {

                        child[h] = k;
                        break;

                    }

                }

            }

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return  child;

    }

    public int [] cycleCrossover(int [][] graph, int [] firstParent, int [] secondParent, int numberOfVertex){

        int [] child = new int[numberOfVertex + 2];

        int index = 1;
        int first = firstParent[index];
        child[1] = firstParent[index];

        while(true){

            int actual = secondParent[index];

            if(actual == first)
                break;

            for(int i = 1; i < numberOfVertex; i++){

                if(actual == firstParent[i]){

                    child[i] = firstParent[i];
                    index = i;

                }

            }

        }

        for(int i = 1; i < numberOfVertex; i++){

            if(child[i] == 0)
                child[i] = secondParent[i];

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int [][] cycleCrossover2(int [][] graph, int [] firstParent, int [] secondParent, int numberOfVertex){

        int [] child = new int[numberOfVertex + 2];
        int [] child2 = new int[numberOfVertex + 2];

        int [][] returnChildren = new int[2][numberOfVertex + 2];

        int position = 1;
        int firstBit = firstParent[1];
        child[position] = secondParent[1];
        int actualValue = secondParent[1];

        boolean test = true;
        boolean condition = true;

        while(condition) {

            do {

                for (int j = 0; j < 3; j++) {

                    int index = 0;

                    for (int i = 1; i < firstParent.length; i++) {

                        if (firstParent[i] == actualValue) {

                            index = i;
                            break;

                        }

                    }

                    actualValue = secondParent[index];

                    if (j == 1) {

                        child2[position] = actualValue;

                        if (actualValue == firstBit)
                            test = false;

                        position++;

                    }

                }

                if(position >= numberOfVertex - 1) {
                    condition = false;
                    break;
                }

                if (test)
                    child[position] = actualValue;
                else
                    break;

            } while (true);

            for (int i = 1; i < numberOfVertex; i++) {

                boolean check = true;

                int tmp = secondParent[i];

                for (int j = 1; j < position; j++) {

                    if (tmp == child[j]) {
                        check = false;
                        break;
                    }

                }

                if (check) {

                    actualValue = tmp;
                    break;

                }

            }

            for (int i = 1; i < numberOfVertex; i++) {

                boolean check = true;

                int tmp = firstParent[i];

                for (int j = 1; j < position; j++) {

                    if (tmp == child2[j]) {
                        check = false;
                        break;
                    }

                }

                if (check) {

                    firstBit = tmp;
                    break;

                }

                child[position] = actualValue;
                test = true;

            }

        }

        for (int j = 0; j < 2; j++) {

            int index = 0;

            for (int i = 1; i < firstParent.length; i++) {

                if (firstParent[i] == actualValue) {

                    index = i;
                    break;

                }

            }

            actualValue = secondParent[index];

            if (j == 1)
                child2[position] = actualValue;

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        child2[0] = 0;
        child2[child2.length - 2] = 0;
        child2[child2.length - 1] = utils.getRouteCost(graph, child2);

        returnChildren[0] = child;
        returnChildren[1] = child2;

        return returnChildren;

    }

}

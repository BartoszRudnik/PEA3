import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Selection {

    private List<int[]> population;

    public List<int[]> getPopulation() {
        return population;
    }

    public void setPopulation(List<int[]> population) {
        this.population = population;
    }

    public Selection(List<int[]> population) {
        this.population = population;
    }

    private void sortPopulation(List<int[]> toSort) {
        toSort.sort(Comparator.comparingInt(o -> o[o.length - 1]));
    }

    public int[] tournament(int numberOfVertex, int populationSize, int n) {

        Random random = new Random();

        boolean[] test = new boolean[populationSize];
        int[] bestRoute = new int[numberOfVertex + 2];
        int bestCost = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int randomIndex = random.nextInt(populationSize - 1);

            if (test[randomIndex]) {
                i--;
                continue;
            } else
                test[randomIndex] = true;

            int[] currentRoute = population.get(randomIndex);

            if (currentRoute[currentRoute.length - 1] < bestCost) {

                bestCost = currentRoute[currentRoute.length - 1];
                bestRoute = currentRoute.clone();

            }

        }

        return bestRoute;

    }

    public int[] ranking(int populationSize) {

        Random random = new Random();

        List<int[]> tmpPopulation = new ArrayList<>(population);
        double[] fitnessValue = new double[populationSize];
        double totalFitnessValue = 0.0;
        double fitnessSum = 0.0;
        int returnIndex = 0;

        sortPopulation(tmpPopulation);

        for (int i = 0; i < populationSize; i++) {

            fitnessValue[i] = (populationSize - i) / (double) populationSize * (populationSize - 1);

        }

        for (int i = 0; i < populationSize; i++)
            totalFitnessValue += fitnessValue[i];

        int helpRank = populationSize;

        for (int i = 0; i < populationSize; i++) {

            fitnessValue[i] = helpRank / totalFitnessValue;
            helpRank--;

        }

        double testValue = random.nextDouble();

        for (int i = 0; i < populationSize; i++) {

            fitnessSum += fitnessValue[i];

            if (fitnessSum >= testValue) {

                returnIndex = i;
                break;

            }

        }

        return tmpPopulation.get(returnIndex);

    }

    public int[] roulette(int populationSize) {

        Random random = new Random();

        List<int[]> tmpPopulation = new ArrayList<>(population);
        double[] fitnessValue = new double[populationSize];
        double totalFitnessValue = 0.0;
        double fitnessSum = 0.0;
        int returnIndex = 0;

        sortPopulation(tmpPopulation);

        for (int i = 0; i < populationSize; i++) {

            int[] route = tmpPopulation.get(i);
            int pathCost = route[route.length - 1];

            fitnessValue[i] = 1 / (double) pathCost;

        }

        for (int i = 0; i < populationSize; i++)
            totalFitnessValue += fitnessValue[i];

        for (int i = 0; i < populationSize; i++)
            fitnessValue[i] /= totalFitnessValue;

        double testValue = random.nextDouble();

        for (int i = 0; i < populationSize; i++) {

            fitnessSum += fitnessValue[i];

            if (fitnessSum >= testValue) {

                returnIndex = i;
                break;

            }

        }

        return tmpPopulation.get(returnIndex);

    }

}

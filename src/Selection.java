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

    //algorytm selekcji turniejowej
    //tworzonych jest n turniejow, do kazdego turnieju losowanych jest n osobnikow,
    //turniej wygrywa osobnik o najmniejszym koszcie przejscia,
    //nastepnie z puli zwyciezcow turniejow wybierany jest najlepszy osobnik
    public int[] tournament(int numberOfVertex, int populationSize, int n) {

        Random random = new Random();

        int[] finalBest = new int[numberOfVertex + 2];
        int finalBestCost = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {

            int[] actualBest = new int[numberOfVertex + 2];
            int actualBestCost = Integer.MAX_VALUE;
            boolean[] test = new boolean[populationSize];

            for (int i = 0; i < n; i++) {

                int randomIndex = random.nextInt(populationSize - 1);

                if (test[randomIndex]) {
                    i--;
                    continue;
                } else
                    test[randomIndex] = true;

                int[] currentRoute = population.get(randomIndex);

                if (currentRoute[currentRoute.length - 1] < actualBestCost) {

                    actualBestCost = currentRoute[currentRoute.length - 1];
                    actualBest = currentRoute.clone();

                }

            }

            if (actualBest[actualBest.length - 1] < finalBestCost) {

                finalBestCost = actualBest[actualBest.length - 1];
                finalBest = actualBest.clone();

            }

        }

        return finalBest;

    }

    //selekcja rankingowa
    //dla kazdego z osobnikow nalezacych do populacji obliczany jest wspolczynnik zdatnosci
    //nastepnie populacja jest sortowana od osobnika o najwiekszym wspolczynniku zdatnosci do osobnika o najmniejszym
    //wspolczynniku zdatnosci, w kolejnym kroku prawdopodobienstwo wyboru danego osobnika ustalane jest wylacznie
    //na podstawie miejsca w rankingu tego osobnika
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

    //selekcja kola ruletki
    //podobnie jak przy selekcji rankingowej dla kazdego z osobnikow obliczany jest wspolczynnik zdatnosci i wykonywane jest sortowanie
    //nastepnie prawdopodobienstwo wyboru danego osobnika ustalane jest na podstawie wspolczynnika zdatnosci tego osobnika
    //im wyzszy wspolczynnik zdatnosci tym wieksza szansa na wybor tego osobnika
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

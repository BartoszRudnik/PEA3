import java.util.List;

public class Main {

    public static void main(String [] args){

        DataReader reader = new DataReader();
        Genetic genetic = new Genetic();

        int [][] graph;
        int numberOfVertex;

        reader.readData2("ftv33.atsp");

        graph = reader.getGraph();
        numberOfVertex = reader.getV();

        List<int[]> population = genetic.getPopulation();

        genetic.generatePopulation(graph, numberOfVertex, 10, 0);

        for (int[] route : population) {

            for (int i : route) {

                System.out.print(i + " ");

            }

            System.out.println();

        }

        System.out.println();

        genetic.sortPopulation();

        for (int[] route : population) {

            for (int i : route) {

                System.out.print(i + " ");

            }

            System.out.println();

        }

        System.out.println();
        genetic.clearPopulation(10, 3);

        for (int[] route : population) {

            for (int i : route) {

                System.out.print(i + " ");

            }

            System.out.println();

        }

        System.out.println();
        genetic.generatePopulation(graph, numberOfVertex, 10, 3);

        for (int[] route : population) {

            for (int i : route) {

                System.out.print(i + " ");

            }

            System.out.println();

        }

    }

}

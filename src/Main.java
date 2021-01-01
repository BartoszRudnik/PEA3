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

        genetic.generatePopulation(graph, numberOfVertex, 150);

        for (int[] route : population) {

            for (int i : route) {

                System.out.print(i + " ");

            }

            System.out.println();

        }

        System.out.println();

        int [] firstParent = genetic.tournamentSelection(numberOfVertex, 150, 10);
        int [] secondParent = genetic.tournamentSelection(numberOfVertex, 150, 10);

        for(int i : firstParent)
            System.out.print(i + " ");

        System.out.println();

        for(int i : secondParent)
            System.out.print(i + " ");

        System.out.println();

        int [] child = genetic.partiallyMappedCrossover(graph, firstParent, secondParent, numberOfVertex);

        boolean [] test = new boolean[child.length];

        for(int i : child) {

            System.out.print(i + " ");

            if(i < test.length) {

                if (test[i])
                    System.out.print("x ");

                test[i] = true;

            }

        }

    }

}

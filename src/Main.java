import java.util.List;

public class Main {

    public static void main(String [] args){

        DataReader reader = new DataReader();
        Genetic genetic = new Genetic();

        int [][] graph;
        int numberOfVertex;

        reader.readData2("ftv47.atsp");

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

        //firstParent = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 0};
        //secondParent = new int[]{0, 2, 7, 5, 8, 4, 1, 6, 3, 0};

        for(int i : firstParent)
            System.out.print(i + " ");

        System.out.println();

        for(int i : secondParent)
            System.out.print(i + " ");

        System.out.println();
        System.out.println();

        int [][] children = genetic.cycleCrossover2(graph, firstParent, secondParent, numberOfVertex);

        int [] child = children[0];
        int [] child2 = children[1];

        boolean [] test = new boolean[child.length];

        for(int i : child) {

            System.out.print(i + " ");

            if(i < test.length) {

                if (test[i])
                    System.out.print("x ");

                test[i] = true;

            }

        }

        System.out.println();
        test = new boolean[child2.length];

        for(int i : child2) {

            System.out.print(i + " ");

            if(i < test.length) {

                if (test[i])
                    System.out.print("x ");

                test[i] = true;

            }

        }

    }

}

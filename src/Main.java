import java.util.ArrayList;
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

        genetic.generatePopulation(graph, numberOfVertex, 100, 0);

        List<int[]> population = genetic.getPopulation();

        for(int i = 0; i < population.size(); i++){

            int [] route = population.get(i);

            for(int j = 0; j < route.length; j++){

                System.out.print(route[j] + " ");

            }

            System.out.println();

        }

    }

}

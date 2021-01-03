public class Main {

    public static void main(String [] args){

        DataReader reader = new DataReader();
        Genetic genetic = new Genetic();

        int [][] graph;
        int numberOfVertex;

        reader.readData2("ftv47.atsp");

        graph = reader.getGraph();
        numberOfVertex = reader.getV();

        genetic.algorithm(graph, numberOfVertex, 10, 150, 10, 0, 5, 0.15, 0);

    }

}

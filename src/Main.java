public class Main {

    public static void main(String [] args){

        DataReader reader = new DataReader();
        UtilsFunction utils = new UtilsFunction();

        int [][] graph;
        int [] result;
        boolean [] test;
        int numberOfVertex;

        reader.readData2("ftv33.atsp");

        graph = reader.getGraph();
        numberOfVertex = reader.getV();

        utils.setNumberOfVertex(numberOfVertex);
        test = new boolean[numberOfVertex];

        result = utils.greedy(graph);

        for(int i = 0; i < result.length; i++) {

            if(test[result[i]])
                System.out.print("bla");

            test[result[i]] = true;

            System.out.print(result[i] + " ");

        }

        System.out.println();

        result = utils.randomGreedy(graph, numberOfVertex / 2);
        test = new boolean[numberOfVertex];

        for(int i = 0; i < result.length; i++) {

            if(test[result[i]])
                System.out.print("bla");

            test[result[i]] = true;

            System.out.print(result[i] + " ");

        }

    }

}

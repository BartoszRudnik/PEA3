public class Main {

    public static void main(String [] args){

        UI ui = new UI();
        ui.showMenu();

       /* DataReader dataReader = new DataReader();
        Genetic genetic = new Genetic();

        dataReader.readData2("ftv47.atsp");

        int [][] graph = dataReader.getGraph();
        int numberOfVertex = dataReader.getV();

        genetic.generatePopulation(graph, numberOfVertex, 50);

        List<int []> population = genetic.getPopulation();

        Selection selection = new Selection(population);
        Mutation mutation = new Mutation(numberOfVertex);
        Crossover crossover = new Crossover(numberOfVertex);
        UtilsFunction utils = new UtilsFunction();

        utils.setNumberOfVertex(numberOfVertex);

        int [] firstParent = selection.tournament(numberOfVertex, 50, 2);
        int [] secondParent = selection.tournament(numberOfVertex, 50, 2);

        firstParent = new int[]{0, 25, 1, 9, 33, 27, 3, 24, 4, 29, 31, 30, 5, 6, 10, 8, 11, 37, 38, 47, 26, 42, 28, 2, 41, 43, 22, 19, 44, 15, 16, 18, 17, 12, 32, 7, 23, 34, 13, 46, 36, 14, 35, 45, 39, 21, 40, 20, 0, 1861};
        secondParent = new int[]{0, 25, 1, 9, 33, 27, 3, 24, 4, 29, 31, 30, 5, 6, 10, 8, 11, 37, 38, 47, 26, 42, 28, 2, 41, 43, 22, 19, 44, 15, 16, 18, 17, 12, 32, 7, 23, 34, 13, 46, 36, 14, 35, 45, 39, 21, 40, 20, 0, 1861};

        for(int i : firstParent)
            System.out.print(i + " ");
        System.out.println();
        System.out.println();

        for(int i : secondParent)
            System.out.print(i + " ");
        System.out.println();
        System.out.println();

        int [] child = crossover.sequentialConstructive(graph, firstParent, secondParent, numberOfVertex);

        for(int i : child)
            System.out.print(i + " ");
        System.out.println();
        System.out.println();

        int [] parameters = mutation.bestRoute(graph, child, numberOfVertex, 2);

        if(parameters[0] != -1 && parameters[1] != -1){

            System.out.println("TAK");

            child = mutation.reverseRoute(child, parameters[0], parameters[1]);
            child[child.length - 1] = utils.getRouteCost(graph, child);

            for(int i : child)
                System.out.print(i + " ");

        }
        else
            System.out.println("NIE");
*/
    }

}

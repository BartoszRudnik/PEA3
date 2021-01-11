public class MeasureUi {

    public void show() {

        Genetic genetic = new Genetic();
        DataReader data = new DataReader();

        data.readData2("rbg403.atsp");

        int[][] graph = data.getGraph();
        int numberOfVertex = data.getV();

        int populationSize = 50;
        int krzyzowanie = 6;
        int mutacja = 0;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 50;
        krzyzowanie = 1;
        mutacja = 2;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 50;
        krzyzowanie = 6;
        mutacja = 2;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 50;
        krzyzowanie = 1;
        mutacja = 0;


        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 150;
        krzyzowanie = 6;
        mutacja = 0;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 150;
        krzyzowanie = 1;
        mutacja = 2;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 150;
        krzyzowanie = 6;
        mutacja = 2;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 150;
        krzyzowanie = 1;
        mutacja = 0;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 300;
        krzyzowanie = 6;
        mutacja = 0;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 300;
        krzyzowanie = 1;
        mutacja = 2;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 300;
        krzyzowanie = 6;
        mutacja = 2;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

        populationSize = 300;
        krzyzowanie = 1;
        mutacja = 0;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, 60, populationSize, 5, 0, krzyzowanie, 0.01, mutacja, 0, 0.8, false);
            System.out.println();

            data.saveResult(populationSize + "_" + krzyzowanie + "_" + mutacja + "_" + "rbg403.atsp", route);

        }

    }

}

public class MeasureUi {

    public void show() {

        Genetic genetic;
        DataReader data = new DataReader();

        data.readData2("ftv170.atsp");
        int populationSize = 150;
        int mutacja = 0;
        double mutationChance = 0.01;
        int seconds = 40;
        int[][] graph = data.getGraph();
        int numberOfVertex = data.getV();

        int krzyzowanie = 6;
        double krzyzowanieSzansa = 0.5;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv170.txt", route);

        }

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.7;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv170.txt", route);

        }

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.9;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv170.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.5;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv170.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.7;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv170.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.9;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv170.txt", route);

        }

        data = new DataReader();
        data.readData2("ftv47.atsp");
        seconds = 20;
        graph = data.getGraph();
        numberOfVertex = data.getV();

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.5;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv47.txt", route);

        }

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.7;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv47.txt", route);

        }

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.9;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv47.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.5;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv47.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.7;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv47.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.9;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "ftv47.txt", route);

        }

        data = new DataReader();
        data.readData2("rbg403.atsp");
        seconds = 60;
        graph = data.getGraph();
        numberOfVertex = data.getV();

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.5;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "rbg403.txt", route);

        }

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.7;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "rbg403.txt", route);

        }

        krzyzowanie = 6;
        krzyzowanieSzansa = 0.9;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "rbg403.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.5;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "rbg403.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.7;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "rbg403.txt", route);

        }

        krzyzowanie = 1;
        krzyzowanieSzansa = 0.9;

        for (int i = 0; i < 10; i++) {

            genetic = new Genetic();
            int[] route = genetic.algorithm(graph, numberOfVertex, seconds, populationSize, 5, 0, krzyzowanie, mutationChance, mutacja, 0, krzyzowanieSzansa, false);
            System.out.println();

            data.saveResult(krzyzowanie + "_" + krzyzowanieSzansa + "_" + "rbg403.txt", route);

        }

    }

}

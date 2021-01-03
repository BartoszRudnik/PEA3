import java.util.Random;

public class UtilsFunction {

    private int numberOfVertex;

    public int getNumberOfVertex() {
        return numberOfVertex;
    }

    public void setNumberOfVertex(int numberOfVertex) {
        this.numberOfVertex = numberOfVertex;
    }

    public int [] randomGreedy(int [][] graph, int n) {

        Random random = new Random();

        int [] route = new int[numberOfVertex + 1];
        int [] resultRoute = new int[numberOfVertex + 1];
        boolean check;
        int oldBestIndex;
        int actualBestIndex = 0;

        route[0] = 0;
        resultRoute[0] = 0;

        for(int i = 1; i <= n; i++){

            boolean test = false;

            while(!test) {

                int count = 0;
                int next = random.nextInt(numberOfVertex - 2) + 1;

                for(int j = 0; j < i; j++)
                    if(resultRoute[j] == next)
                        count++;

                if(count == 0) {
                    resultRoute[i] = next;
                    route[i] = next;
                    test = true;
                }

            }

        }

        boolean test = false;

        while(!test) {

            int count = 0;
            int next = random.nextInt(numberOfVertex - 2) + 1;

            for(int j = 0; j < n; j++)
                if(resultRoute[j] == next)
                    count++;

            if(count == 0) {
                actualBestIndex = next;
                test = true;
            }

        }

        for(int i = n + 1; i < numberOfVertex; i++){

            int bestCost = Integer.MAX_VALUE;
            oldBestIndex = actualBestIndex;

            for(int j = 0; j < numberOfVertex; j++){

                check = true;

                for(int g = 0; g <= i; g++){

                    if (j == resultRoute[g]) {
                       check = false;
                       break;
                    }

                }

                if(graph[oldBestIndex][j] < bestCost && check && j != oldBestIndex){

                   bestCost = graph[oldBestIndex][j];
                   actualBestIndex = j;

                }

            }

            route[i] = actualBestIndex;
            resultRoute[i] = oldBestIndex;

        }

        resultRoute[numberOfVertex] = 0;

        return resultRoute;

    }

    public int [] greedy(int [][] graph){

        int [] route = new int[numberOfVertex + 1];
        int [] resultRoute = new int[numberOfVertex + 1];
        boolean check;
        int oldBestIndex;
        int actualBestIndex = 0;

        for(int i = 0; i < numberOfVertex; i++){

            int bestCost = Integer.MAX_VALUE;
            oldBestIndex = actualBestIndex;

            for(int j = 0; j < numberOfVertex; j++){

                check = true;

                if(j != oldBestIndex){

                    for(int g = 0; g <= i; g++){

                        if (j == route[g]) {
                            check = false;
                            break;
                        }

                    }
                    if(graph[oldBestIndex][j] < bestCost && check){

                        bestCost = graph[oldBestIndex][j];
                        actualBestIndex = j;

                    }

                }

            }

            route[i] = actualBestIndex;
            resultRoute[i] = oldBestIndex;

        }

        resultRoute[numberOfVertex] = 0;

        return resultRoute;

    }

    public int [] shuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();

        int [] tmpArray = new int[array.length - 2];

        if (tmpArray.length >= 0)
            System.arraycopy(array, 1, tmpArray, 0, tmpArray.length);

        for (int i = tmpArray.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = tmpArray[index];
            tmpArray[index] = tmpArray[i];
            tmpArray[i] = temp;
        }

        if (tmpArray.length >= 0)
            System.arraycopy(tmpArray, 0, array, 1, tmpArray.length);

        return  array;

    }

    public int getRouteCost(int [][] graph, int [] route){

        int cost = 0;

        for(int i = 1; i <= numberOfVertex; i++){

            cost += graph[route[i - 1]][route[i]];

        }

        return cost;

    }

    public void getResultRoute(int [] route){

        for(int i = 0; i <= numberOfVertex; i++){

            System.out.print(route[i]);

            if(i != numberOfVertex)
                System.out.print("-");
            else
                System.out.println();

        }

    }

    public boolean checkRoute(int [] route){

        boolean [] check = new boolean[numberOfVertex];

        for(int i = 0; i < numberOfVertex; i++){

            if(check[route[i]])
                return false;

            check[route[i]] = true;

        }

        return true;

    }

}

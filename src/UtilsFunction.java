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

    public int [] reverseRoute(int [] route, int i, int j){

        int [] index = getIndex(route, i, j);
        int iIndex = index[0];
        int jIndex = index[1];

        if(iIndex < jIndex){
            while(iIndex < jIndex){

                int tmp = route[iIndex];
                route[iIndex] = route[jIndex];
                route[jIndex] = tmp;

                iIndex++;
                jIndex--;

            }
        }
        else
        {
            while(jIndex < iIndex){

                int tmp = route[iIndex];
                route[iIndex] = route[jIndex];
                route[jIndex] = tmp;

                iIndex--;
                jIndex++;

            }
        }

        return route;

    }

    public int [] swapRoute(int [] route, int i, int j){

        int [] index = getIndex(route, i, j);
        int iIndex = index[0];
        int jIndex = index[1];

        int tmp = route[iIndex];

        route[iIndex] = route[jIndex];
        route[jIndex] = tmp;

        return  route;

    }

    public int [] insertRoute(int [] route, int i, int j){

        int indexI = 0;
        int tmp = 0;
        int [] tmpArray = new int[route.length];

        for(int k = 1; k < route.length - 1; k++){

            if(route[k] == i){

                indexI = k;
                break;

            }

        }

        if(indexI > j) {

            for (int k = j; k < route.length; k++) {

                if (route[k] != i)
                    tmpArray[k - tmp] = route[k];
                else
                    tmp = 1;

            }

            route[j] = i;

            if (route.length - (j + 1) >= 0)
                System.arraycopy(tmpArray, j + 1 - 1, route, j + 1, route.length - (j + 1));

        }
        else{

            if (j - indexI >= 0)
                System.arraycopy(route, indexI + 1, route, indexI, j - indexI);

            route[j] = i;

        }

        return  route;

    }

    private int [] getIndex(int [] route, int i, int j){

        int [] index = new int[2];

        for(int k = 1 ; k < route.length - 1; k++){

            if(route[k] == i)
                index[0] = k;

            if(route[k] == j)
                index[1] = k;

        }

        return index;

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

public class Mutation {

    public int[] reverseRoute(int[] route, int i, int j) {

        int[] index = getIndex(route, i, j);
        int iIndex = index[0];
        int jIndex = index[1];

        if (iIndex < jIndex) {
            while (iIndex < jIndex) {

                int tmp = route[iIndex];
                route[iIndex] = route[jIndex];
                route[jIndex] = tmp;

                iIndex++;
                jIndex--;

            }
        } else {
            while (jIndex < iIndex) {

                int tmp = route[iIndex];
                route[iIndex] = route[jIndex];
                route[jIndex] = tmp;

                iIndex--;
                jIndex++;

            }
        }

        return route;

    }

    public int[] swapRoute(int[] route, int i, int j) {

        int[] index = getIndex(route, i, j);
        int iIndex = index[0];
        int jIndex = index[1];

        int tmp = route[iIndex];

        route[iIndex] = route[jIndex];
        route[jIndex] = tmp;

        return route;

    }

    public int[] insertRoute(int[] route, int i, int j) {

        int indexI = 0;
        int tmp = 0;
        int[] tmpArray = new int[route.length];

        for (int k = 1; k < route.length - 1; k++) {

            if (route[k] == i) {

                indexI = k;
                break;

            }

        }

        if (indexI > j) {

            for (int k = j; k < route.length; k++) {

                if (route[k] != i)
                    tmpArray[k - tmp] = route[k];
                else
                    tmp = 1;

            }

            route[j] = i;

            if (route.length - (j + 1) >= 0)
                System.arraycopy(tmpArray, j + 1 - 1, route, j + 1, route.length - (j + 1));

        } else {

            if (j - indexI >= 0)
                System.arraycopy(route, indexI + 1, route, indexI, j - indexI);

            route[j] = i;

        }

        return route;

    }

    private int[] getIndex(int[] route, int i, int j) {

        int[] index = new int[2];

        for (int k = 1; k < route.length - 1; k++) {

            if (route[k] == i)
                index[0] = k;

            if (route[k] == j)
                index[1] = k;

        }

        return index;

    }

}

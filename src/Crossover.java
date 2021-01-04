import java.util.Random;

public class Crossover {

    UtilsFunction utils = new UtilsFunction();

    public UtilsFunction getUtils() {
        return utils;
    }

    public void setUtils(UtilsFunction utils) {
        this.utils = utils;
    }

    public Crossover(int numberOfVertex) {

        utils.setNumberOfVertex(numberOfVertex);

    }

    public int[] singlePoint(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        Random random = new Random();

        int border = random.nextInt(numberOfVertex - 2) + 1;

        System.arraycopy(firstParent, 1, child, 1, border);

        int index = border + 1;

        for (int i = 0; i < numberOfVertex; i++) {

            boolean add = true;

            if (index == numberOfVertex - 1)
                index = 1;

            int tmp = secondParent[i];

            for (int j = 1; j < numberOfVertex; j++) {

                if (tmp == child[j]) {

                    add = false;
                    break;

                }

            }

            if (add) {
                child[index] = tmp;
                index++;
            }

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] twoPoint(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[firstParent.length];

        Random random = new Random();
        int i = 0;
        int j = 0;

        while (i == j) {

            i = random.nextInt(numberOfVertex - 2) + 1;
            j = random.nextInt(numberOfVertex - 2) + 1;

        }

        if (j > i) {

            int tmp = i;
            i = j;
            j = tmp;

        }

        int[] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for (int start = j; start <= i; start++) {

            chosenVertexes[tmpIndex] = firstParent[start];
            tmpIndex++;

        }

        tmpIndex = 0;

        for (int start = 0; start < firstParent.length - 2; start++) {

            boolean add = true;
            int tmp = secondParent[start];

            for (int chosenVertex : chosenVertexes)
                if (tmp == chosenVertex) {
                    add = false;
                    break;
                }

            if (add) {
                child[tmpIndex] = tmp;
                tmpIndex++;
            }

        }

        int tmpIndex2 = chosenVertexes.length - 1;

        for (int start = tmpIndex; start < firstParent.length - 2; start++) {

            child[start] = chosenVertexes[tmpIndex2];
            tmpIndex2--;

        }

        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] order(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        Random random = new Random();
        int i = 0;
        int j = 0;

        while (i == j) {

            i = random.nextInt(numberOfVertex - 2) + 1;
            j = random.nextInt(numberOfVertex - 2) + 1;

        }

        if (j > i) {

            int tmp = i;
            i = j;
            j = tmp;

        }

        int[] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for (int start = j; start <= i; start++) {

            chosenVertexes[tmpIndex] = firstParent[start];
            tmpIndex++;

        }

        tmpIndex = i + 1;
        int tmpIndex2 = i + 1;

        if (tmpIndex == secondParent.length - 2)
            tmpIndex = 1;

        if (tmpIndex2 == secondParent.length - 2)
            tmpIndex2 = 1;

        for (int k = 0; k < numberOfVertex; k++) {

            int tmpVertex = secondParent[tmpIndex2];
            boolean test = true;

            for (int chosenVertex : chosenVertexes) {

                if (tmpVertex == chosenVertex) {
                    test = false;
                    break;
                }

            }

            if (test) {
                child[tmpIndex] = tmpVertex;
                tmpIndex++;
            }

            tmpIndex2++;

            if (tmpIndex == secondParent.length - 2)
                tmpIndex = 1;

            if (tmpIndex2 == secondParent.length - 2)
                tmpIndex2 = 1;

        }

        tmpIndex2 = 0;

        for (int k = j; k <= i; k++) {

            child[k] = chosenVertexes[tmpIndex2];
            tmpIndex2++;

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] partiallyMapped(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        Random random = new Random();
        int i = 0;
        int j = 0;

        while (i == j) {

            i = random.nextInt(numberOfVertex - 2) + 1;
            j = random.nextInt(numberOfVertex - 2) + 1;

        }

        if (j > i) {

            int tmp = i;
            i = j;
            j = tmp;

        }

        int[] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for (int k = j; k <= i; k++) {

            chosenVertexes[tmpIndex] = secondParent[k];
            child[k] = secondParent[k];
            tmpIndex++;

        }

        for (int k = 1; k < numberOfVertex; k++) {

            boolean test = true;

            for (int chosenVertex : chosenVertexes) {

                if (firstParent[k] == chosenVertex) {

                    test = false;
                    break;

                }

            }

            if (test && (k < j || k > i))
                child[k] = firstParent[k];


        }

        for (int k = 1; k < numberOfVertex; k++) {

            boolean test = true;

            for (int v : child) {

                if (k == v) {

                    test = false;
                    break;

                }

            }

            if (test) {

                for (int h = 1; h < numberOfVertex; h++) {

                    if (child[h] == 0) {

                        child[h] = k;
                        break;

                    }

                }

            }

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] cycle(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        int index = 1;
        int first = firstParent[index];
        child[1] = firstParent[index];

        while (true) {

            int actual = secondParent[index];

            if (actual == first)
                break;

            for (int i = 1; i < numberOfVertex; i++) {

                if (actual == firstParent[i]) {

                    child[i] = firstParent[i];
                    index = i;

                }

            }

        }

        for (int i = 1; i < numberOfVertex; i++) {

            if (child[i] == 0)
                child[i] = secondParent[i];

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[][] cycle2(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];
        int[] child2 = new int[numberOfVertex + 2];

        int[][] returnChildren = new int[2][numberOfVertex + 2];

        int position = 1;
        int firstBit = firstParent[1];
        child[position] = secondParent[1];
        int actualValue = secondParent[1];

        boolean test = true;
        boolean condition = true;

        while (condition) {

            do {

                for (int j = 0; j < 3; j++) {

                    int index = 0;

                    for (int i = 1; i < firstParent.length; i++) {

                        if (firstParent[i] == actualValue) {

                            index = i;
                            break;

                        }

                    }

                    actualValue = secondParent[index];

                    if (j == 1) {

                        child2[position] = actualValue;

                        if (actualValue == firstBit)
                            test = false;

                        position++;

                    }

                }

                if (position >= numberOfVertex - 1) {
                    condition = false;
                    break;
                }

                if (test)
                    child[position] = actualValue;
                else
                    break;

            } while (true);

            for (int i = 1; i < numberOfVertex; i++) {

                boolean check = true;

                int tmp = secondParent[i];

                for (int j = 1; j < position; j++) {

                    if (tmp == child[j]) {
                        check = false;
                        break;
                    }

                }

                if (check) {

                    actualValue = tmp;
                    break;

                }

            }

            for (int i = 1; i < numberOfVertex; i++) {

                boolean check = true;

                int tmp = firstParent[i];

                for (int j = 1; j < position; j++) {

                    if (tmp == child2[j]) {
                        check = false;
                        break;
                    }

                }

                if (check) {

                    firstBit = tmp;
                    break;

                }

                child[position] = actualValue;
                test = true;

            }

        }

        for (int j = 0; j < 2; j++) {

            int index = 0;

            for (int i = 1; i < firstParent.length; i++) {

                if (firstParent[i] == actualValue) {

                    index = i;
                    break;

                }

            }

            actualValue = secondParent[index];

            if (j == 1)
                child2[position] = actualValue;

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        child2[0] = 0;
        child2[child2.length - 2] = 0;
        child2[child2.length - 1] = utils.getRouteCost(graph, child2);

        returnChildren[0] = child;
        returnChildren[1] = child2;

        return returnChildren;

    }

    public int[] sequentialConstructive(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        int position = 1;

        int addNode = firstParent[position];
        int firstNode;
        int secondNode;

        int firstParentIndex;
        int secondParentIndex;

        child[position] = addNode;
        position++;

        while (position < numberOfVertex) {

            firstParentIndex = sequentialIndex(numberOfVertex, firstParent, addNode);
            secondParentIndex = sequentialIndex(numberOfVertex, secondParent, addNode);

            firstNode = sequentialNode(numberOfVertex, firstParentIndex, position, firstParent, child);
            secondNode = sequentialNode(numberOfVertex, secondParentIndex, position, secondParent, child);

            if (graph[addNode][firstNode] < graph[addNode][secondNode]) {

                child[position] = firstNode;
                addNode = firstNode;

            } else {

                child[position] = secondNode;
                addNode = secondNode;

            }

            position++;

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] enhancedSequentialConstructive(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        int position = 1;

        int addNode = firstParent[position];
        int firstNode;
        int secondNode;

        int firstParentIndex;
        int secondParentIndex;

        child[position] = addNode;
        position++;

        while (position < numberOfVertex) {

            firstParentIndex = sequentialIndex(numberOfVertex, firstParent, addNode);
            secondParentIndex = sequentialIndex(numberOfVertex, secondParent, addNode);

            firstNode = sequentialNode(numberOfVertex, firstParentIndex, position, firstParent, child);
            secondNode = sequentialNode(numberOfVertex, secondParentIndex, position, secondParent, child);

            int firstParentMin = minRowValue(firstNode, graph, numberOfVertex, child);
            int secondParentMin = minRowValue(secondNode, graph, numberOfVertex, child);

            if (graph[addNode][firstNode] + firstParentMin < graph[addNode][secondNode] + secondParentMin) {

                child[position] = firstNode;
                addNode = firstNode;

            } else {

                child[position] = secondNode;
                addNode = secondNode;

            }

            position++;

        }

        child[0] = 0;
        child[child.length - 2] = 0;
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    private int sequentialIndex(int numberOfVertex, int[] parent, int addNode) {

        int index = 0;

        for (int i = 1; i < numberOfVertex; i++) {

            if (parent[i] == addNode) {

                index = i;
                break;

            }

        }

        return index;

    }

    private int sequentialNode(int numberOfVertex, int parentIndex, int position, int[] parent, int[] child) {

        int node = 0;

        for (int i = 0; i < numberOfVertex; i++) {

            if (parentIndex + 1 == numberOfVertex)
                parentIndex = 0;

            boolean check = true;
            int tmp = parent[parentIndex + 1];

            for (int j = 1; j < position; j++) {

                if (child[j] == tmp || tmp == 0) {
                    check = false;
                    break;
                }

            }

            if (check) {

                node = tmp;
                break;

            }

            parentIndex++;

        }

        return node;

    }

    private int minRowValue(int row, int[][] graph, int numberOfVertex, int[] child) {

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfVertex; i++) {

            boolean check = true;

            for (int j = 0; j < numberOfVertex; j++) {

                if (i == child[j]) {

                    check = false;
                    break;

                }

            }

            if (graph[row][i] < min && check)
                min = graph[row][i];

        }

        return min;

    }

}

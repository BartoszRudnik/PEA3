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

        //wyznaczamy punkt podzialu
        int border = random.nextInt(numberOfVertex - 2) + 1;

        //kopiujemy do dziecka z pierwszego rodzica elementy z indeksow [1, border]
        System.arraycopy(firstParent, 1, child, 1, border);

        int index = border + 1;

        //nastepnie uzepelniamy dziecko takimi elementami z drugiego rodzica, ktore nie zostaly skopiowane z pierwszego rodzica
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

        //dodajemy '0' na poczatek i koniec sciezki
        child[0] = 0;
        child[child.length - 2] = 0;
        //obliczamy koszt przejscia sciezki
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] twoPoint(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[firstParent.length];

        //wyznaczamy indeksy wedlug ktorych stworzona zostanie sekcja dopasowania dla pierwszego rodzica
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

        //zapamietujemy wierzcholki, ktore znalazly sie w sekcji dopasowania pierwszego rodzica
        int[] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for (int start = j; start <= i; start++) {

            chosenVertexes[tmpIndex] = firstParent[start];
            tmpIndex++;

        }

        //tworzymy dziecko poprzez usuniecie z drugiego rodzica wierzcholkow nalezacych do sekcji dopasowania pierwszego rodzica
        //wierzcholki, ktore nie zostaly usuniete z drugiego rodzica przesuwamy na poczatek sciezki
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

        //na koniec sciezki dodajemy wierzcholki nalezace do sekcji dopasowania pierwszego rodzica
        int tmpIndex2 = chosenVertexes.length - 1;

        for (int start = tmpIndex; start < firstParent.length - 2; start++) {

            child[start] = chosenVertexes[tmpIndex2];
            tmpIndex2--;

        }

        child[child.length - 2] = 0;
        //obliczamy koszt przejscia stworzonej sciezki
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] order(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        //losujemy dwa indeksy pomiedzy ktorymi stworzona zostanie sekcja dopasowania
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

        //zapamietujemy wierzcholki nalezace do sekcji dopasowania pierwszego rodzica
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

        //nastepnie kopiujemy do dziecka wierzcholki nie wystepujace do tej pory w dziecku,
        //nalezace do drugiego rodzica, zaczynajac od wierzcholka znajdujacego
        //sie na pozycji (i + 1), gdzie i to koncowy indeks sekcji dopasowania
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

        //dodajemy wierzcholek poczatkowy i koncowy
        child[0] = 0;
        child[child.length - 2] = 0;
        //obliczamy koszt przejscia stworzonej sciezki
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[] partiallyMapped(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        //wyznaczamy dwa indeksy na podstawie ktorych stworzona zostanie sekcja dopasowania
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

        //zapamietujemy wierzcholki drugiego rodzica, ktore naleza do sekcji dopasowania
        //wierzcholki te beda skopiowane do dziecka
        int[] chosenVertexes = new int[i - j + 1];
        int tmpIndex = 0;

        for (int k = j; k <= i; k++) {

            chosenVertexes[tmpIndex] = secondParent[k];
            child[k] = secondParent[k];
            tmpIndex++;

        }

        //przegladamy wierzcholki pierwszego rodzica, ktore nie naleza do sekcji dopasowania,
        //jesli nie zachodza konflikty to umieszczamy je w dziecku
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

        //dodajemy wierzcholek startowy i koncowy
        child[0] = 0;
        child[child.length - 2] = 0;
        //obliczamy koszt przejscia stworzonego dziecka
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }


    public int[] cycle(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        int index = 1;
        int first = firstParent[index];
        child[1] = firstParent[index];

        //najpierw do dziecka kopiujemy pierwszy wierzcholek znajdujacy sie w pierwszym rodzicu
        //nastepnie sprawdzamy, ktory wierzcholek w rodzicu drugim znajduje sie na pozycji o tym samym indeksie,
        //kolejnym krokiem jest odnalezenie tego wierzcholka w rodzicu pierwszym i wstawienie go do dziecka na ta sama
        //pozycje, w ktorej jest umieszczony w rodzicu pierwszym
        //powtarzamy te kroki az do momentu wystapienia cyklu
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

        //nastepnie na puste pozycje w dziecku umieszczamy wierzcholki znajdujace sie na odpowiadajacych im
        //pozycjom wierzcholkom w drugim rodzicu
        for (int i = 1; i < numberOfVertex; i++) {

            if (child[i] == 0)
                child[i] = secondParent[i];

        }

        //uzupelniamy wierzcholek startowy i koncowy
        child[0] = 0;
        child[child.length - 2] = 0;
        //obliczamy koszt przejscia stworzonego dziecka
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }

    public int[][] cycle2(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];
        int[] child2 = new int[numberOfVertex + 2];

        int[][] returnChildren = new int[2][numberOfVertex + 2];

        //kopiujemy pierwszy wierzcholek drugiego rodzica jako pierwszy wierzcholek pierwszego dziecka
        int position = 1;
        int firstBit = firstParent[1];
        child[position] = secondParent[1];
        int actualValue = secondParent[1];

        boolean test = true;
        boolean condition = true;

        //dopoki pierwszy wierzcholek pierwszego rodzica nie zostanie umieszczony w drugim dziecku
        while (condition) {

            //znajdujemy pierwszy wierzcholek drugiego rodzica w pierwszym rodzicu i wybieramy z tej samej pozycji
            //wierzcholek z drugiego rodzica i odnajdujemy go w pierwszym rodzicu i umieszczamy w drugim dziecku
            //powtarzamy czynnosci az do spelnienia warunku 'condition'
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


            //uzupelniamy wierzcholki brakujace w dzieciach
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

        //uzupelniamy wierzcholek startowy i koncowy oraz obliczamy koszt przejscia wytworzonych dzieci
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

        //dodajemy do dziecka pierwszy wierzcholek pierwszego rodzica
        int addNode = firstParent[position];
        int firstNode;
        int secondNode;

        int firstParentIndex;
        int secondParentIndex;

        child[position] = addNode;
        position++;

        //wyszukujemy w obu rodzicach wierzcholki wystepujace po ostatnio dodanym do dziecka wierzcholku (jesli nie ma takich
        //wierzcholkow wybieramy pierwszy z kolei nie dodany jeszcze wierzcholek)
        //nastepnie sprawdzamy w zadanym grafie wejsciowym, ktore z dwoch przejsc jest tansze i dodajemy do dziecka
        //wierzcholek odpowiadajacy temu przejsciu
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

        //dodajemy wierzcholek koncowy i poczatkowy
        child[0] = 0;
        child[child.length - 2] = 0;
        //dodajemy koszt przejscia utworzonego dziecka
        child[child.length - 1] = utils.getRouteCost(graph, child);

        return child;

    }


    public int[] enhancedSequentialConstructive(int[][] graph, int[] firstParent, int[] secondParent, int numberOfVertex) {

        int[] child = new int[numberOfVertex + 2];

        int position = 1;

        //dodajemy do dziecka pierwszy wierzcholek pierwszego rodzica
        int addNode = firstParent[position];
        int firstNode;
        int secondNode;

        int firstParentIndex;
        int secondParentIndex;

        child[position] = addNode;
        position++;

        //dopoki nie dodamy wszystkich wierzcholkow do dziecka
        while (position < numberOfVertex) {

            //znajdujemy w pierwszym i drugim rodzicu wierzcholki wystepujace po ostatnim dodanym do dziecka wierzcholku

            firstParentIndex = sequentialIndex(numberOfVertex, firstParent, addNode);
            secondParentIndex = sequentialIndex(numberOfVertex, secondParent, addNode);

            firstNode = sequentialNode(numberOfVertex, firstParentIndex, position, firstParent, child);
            secondNode = sequentialNode(numberOfVertex, secondParentIndex, position, secondParent, child);

            //obliczamy najtanszy koszt przejscia z pierwszego rozpatrywanego wierzcholka do dowolnego nieodwiedzonego jeszcze wierzcholka
            int firstParentMin = minRowValue(firstNode, graph, numberOfVertex, child);
            //obliczamy najtanszy koszt przejscia z drugiego ropzatrywanego wierzcholka do dowolnego nieodwiedzonego jeszcze wierzcholka
            int secondParentMin = minRowValue(secondNode, graph, numberOfVertex, child);

            //obliczamy sume kosztu przejscia z ostatnio dodanego wierzcholka do pierwszego rozpatrywanego wierzcholka i najtanszy koszt przejscia z pierwszego rozpatrywanego wierzcholka do nieodwiedzonego jeszcze wierzcholka
            //obliczamy sume kosztu przejscia z ostatnio dodanego wierzcholka do drugiego rozpatrywanego wierzcholka i najtanszy koszt przejscia z drugiego rozpatrywanego wierzcholka do nieodwiedzonego jeszcze wierzcholka
            //rozwazany wierzcholek dla ktorego suma jest mniejsza dodawany jest do dziecka
            if (graph[addNode][firstNode] + firstParentMin < graph[addNode][secondNode] + secondParentMin) {

                child[position] = firstNode;
                addNode = firstNode;

            } else {

                child[position] = secondNode;
                addNode = secondNode;

            }

            position++;

        }

        //dodajemy wierzcholek startowy i koncowy
        child[0] = 0;
        child[child.length - 2] = 0;
        //obliczamy koszt przejscia stworzonego dziecka
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

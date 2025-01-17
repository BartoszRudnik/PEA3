import java.util.Scanner;

public class TestUi {

    private final DataReader data = new DataReader();

    private int[][] graph;
    private int numberOfVertex;
    private int seconds = 10;
    private int populationSize = 150;
    private int exclusivity = 10;
    private int selectionType = 0;
    private int crossoverType = 0;
    private int mutationType = 0;
    private int memeticType = 0;
    private double crossoverChance = 0.8;
    private double mutationChance = 0.15;
    private boolean memetic = false;

    private boolean spr = true;

    public void show() {

        Scanner scanner = new Scanner(System.in);

        while (spr) {

            System.out.println("Wybierz operacje");
            System.out.println("1. Algorytm Genetyczny");
            System.out.println("2. Wczytaj dane");
            System.out.println("3. Ustaw liczbe sekund");
            System.out.println("4. Ustaw rozmiar populacji");
            System.out.println("5. Ustaw elitaryzm");
            System.out.println("6. Ustaw rodzaj selekcji rodzicow");
            System.out.println("7. Ustaw rodzaj krzyzowania");
            System.out.println("8. Ustaw szanse mutacji");
            System.out.println("9. Ustaw rodzaj mutacji");
            System.out.println("10. Ustaw memetic type");
            System.out.println("11. Ustaw szanse krzyzowania");
            System.out.println("12. Czy wlaczyc memetic");
            System.out.println("13. Sprawdz aktualne ustawienia");
            System.out.println("14. Wyswietl wczytane dane");
            System.out.println("0. Wyjdz");

            int opNr = scanner.nextInt();

            switch (opNr) {
                case 0:
                    spr = false;
                    break;

                case 1:
                    Genetic genetic = new Genetic();
                    try {

                        if (numberOfVertex == 0)
                            throw new Exception();

                        genetic.algorithm(graph, numberOfVertex, seconds, populationSize, exclusivity, selectionType, crossoverType, mutationChance, mutationType, memeticType, crossoverChance, memetic);
                        System.out.println();

                    } catch (Exception ex) {
                        System.out.println("Nie zostal wczytany graf");
                        ex.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Podaj nazwe pliku do wczytania");
                    scanner.nextLine();
                    String fileName = scanner.nextLine();
                    data.readData2(fileName);
                    graph = data.getGraph();
                    numberOfVertex = data.getV();
                    break;

                case 3:
                    System.out.println("Podaj czas wykonywania sie algorytmu w sekundach: ");
                    try {

                        scanner.nextLine();
                        seconds = scanner.nextInt();

                        if (seconds <= 0) {
                            seconds = 10;
                            throw new Exception();
                        }

                    } catch (Exception ex) {

                        System.out.println("Podano bledna wartosc");

                    }
                    break;

                case 4:
                    System.out.println("Podaj rozmiar populacji: ");
                    try {

                        scanner.nextLine();
                        populationSize = scanner.nextInt();

                        if (populationSize <= 0) {
                            populationSize = 150;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 5:
                    System.out.println("Podaj rozmiar elitaryzmu: ");
                    try {

                        scanner.nextLine();
                        exclusivity = scanner.nextInt();

                        if (exclusivity <= 0 || exclusivity >= populationSize) {
                            exclusivity = 10;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 6:
                    System.out.println("Podaj rodzaj selekcji rodzicow: ");
                    System.out.println("_______________");
                    System.out.println("0. Tournament");
                    System.out.println("1. Ranking");
                    System.out.println("2. Roulette");
                    System.out.println("_______________");
                    try {

                        scanner.nextLine();
                        selectionType = scanner.nextInt();

                        if (selectionType < 0 || selectionType > 2) {
                            selectionType = 0;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 7:
                    System.out.println("Podaj rodzaj krzyzowania: ");
                    System.out.println("_______________");
                    System.out.println("0. Two Point");
                    System.out.println("1. Order");
                    System.out.println("2. Partially Mapped");
                    System.out.println("3. Cycle");
                    System.out.println("4. Cycle 2");
                    System.out.println("5. Sequential Constructive");
                    System.out.println("6. Enhanced Sequential Constructive");
                    System.out.println("7. Single point");
                    System.out.println("_______________");
                    try {

                        scanner.nextLine();
                        crossoverType = scanner.nextInt();

                        if (crossoverType < 0 || selectionType > 7) {
                            crossoverType = 0;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 8:
                    System.out.println("Podaj szanse mutacji: ");
                    try {

                        scanner.nextLine();
                        mutationChance = scanner.nextDouble();

                        if (mutationChance < 0 || mutationChance > 1) {
                            mutationChance = 0.15;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 9:
                    System.out.println("Wybierz rodzaj mutacji:");
                    System.out.println("_______________");
                    System.out.println("0. Insert");
                    System.out.println("1. Swap");
                    System.out.println("2. Reverse");
                    System.out.println("_______________");
                    try {

                        scanner.nextLine();
                        mutationType = scanner.nextInt();

                        if (mutationType < 0 || mutationType > 2) {
                            mutationType = 2;
                            throw new Exception();
                        }


                    } catch (Exception ex) {
                        System.out.println("Wybrano zly rodzaj sasiedztwa");
                    }
                    break;

                case 10:
                    System.out.println("Podaj memetic type: ");
                    System.out.println("_______________");
                    System.out.println("0. Insert");
                    System.out.println("1. Swap");
                    System.out.println("2. Reverse");
                    System.out.println("_______________");
                    try {

                        scanner.nextLine();
                        memeticType = scanner.nextInt();

                        if (memeticType < 0 || memeticType > 2) {
                            memeticType = 0;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 11:
                    System.out.println("Podaj szanse krzyzowania: ");
                    try {

                        scanner.nextLine();
                        crossoverChance = scanner.nextDouble();

                        if (crossoverChance < 0 || crossoverChance > 1) {
                            crossoverChance = 0.80;
                            throw new Exception();
                        }

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }
                    break;

                case 12:
                    System.out.println("1. TAK");
                    System.out.println("2. NIE");

                    try {

                        scanner.nextLine();

                        int tmp = scanner.nextInt();

                        if (tmp == 1)
                            memetic = true;
                        else if (tmp == 2)
                            memetic = false;
                        else
                            throw new Exception();

                    } catch (Exception ex) {
                        System.out.println("Podano bledna wartosc");
                    }

                    break;

                case 13:
                    System.out.println("---------------------------");
                    System.out.println("Aktualne ustawienia: ");
                    System.out.println("Liczba wierzcholkow grafu: " + numberOfVertex);
                    System.out.println("Liczba sekund: " + seconds);
                    System.out.println("Rozmiar populacji: " + populationSize);
                    System.out.println("Kryterium elitaryzmu: " + exclusivity);
                    System.out.println("Rodzaj wyboru rodzica: " + selectionType);
                    System.out.println("Rodzaj krzyzowania: " + crossoverType);
                    System.out.println("Szansa na mutacje: " + mutationChance);
                    System.out.println("Rodzaj mutacji: " + mutationType);
                    System.out.println("Memetic type: " + memeticType);
                    System.out.println("Memetic: " + memetic);
                    System.out.println("Szansa krzyzowania: " + crossoverChance);
                    System.out.println("---------------------------");
                    break;

                case 14:
                    data.printData();
                    break;

                default:
                    System.out.println("Wybrano bledna operacje");
                    break;

            }

        }

    }

}

import java.util.Scanner;

public class UI {

    Scanner scanner = new Scanner(System.in);

    TestUi test;
    MeasureUi measure;

    public void showMenu() {

        while (true) {

            System.out.println("1. Testowanie");
            System.out.println("2. Pomiary");
            System.out.println("0. Zakoncz");

            int nrOperacji = scanner.nextInt();

            switch (nrOperacji) {

                case 0:
                    System.out.println("KONIEC PROGRAMU");
                    System.exit(0);
                    break;

                case 1:
                    test = new TestUi();
                    test.show();
                    break;

                case 2:
                    measure = new MeasureUi();
                    measure.show();
                    break;

                default:
                    System.out.println("Podano zly numer");
                    break;

            }

        }

    }

}



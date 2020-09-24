import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.*;

public class Runner {
    public static void main(String[] args) {

        //Making Instance of readingBooksFromFile and using method for reading it. In the method .redBooksFromFile() we are adding Books to the list which is called here (beacuse of static field)
        BooksFromFile booksFromFile = new BooksFromFile();
        booksFromFile.readBooksFromFile();
        List<Book> libraryWithBooks = BooksFromFile.bookInFile;

        String operatorMenu;
        String statementForWhile = "n";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w księgarni");

        //Loop will shut down if user write y or Y. Either if he write "y " or " y"
        while (!statementForWhile.equals("y")) {

            //Menu text
            System.out.println("Wybierz jedną z poniżej dostępnych opcji poprzez wpisanie w konsolę wybranej liczby");
            System.out.println("1. Wypisz książkę z największą liczbą stron");
            System.out.println("2. Posortuj i wypisz książki według roku wydania (od najnowszej)");
            System.out.println("3. Wypisz książki w zależności od podanego typu");
            System.out.println("4. Wypisz książkę w zależności od podanego numeru ISBN");
            System.out.println("5. Wypisz książki w zależności od podanego autora");
            System.out.println();
            System.out.print("Twój wybór to: ");
            operatorMenu = scanner.nextLine();

            //Switch operator for options above. All of them is in stream.
            switch (operatorMenu) {
                case ("1"):
                    try {
                        List<Book> maxPages = libraryWithBooks.stream()
                                .sorted(comparing(Book::getNumberOfPages).reversed())
                                .collect(Collectors.toList());

                        System.out.println(maxPages.get(0));
                    } catch (Exception e) {
                        System.out.println("Przepraszamy, wystąpił nieoczekiwany błąd: " + e.getMessage());
                        e.getStackTrace();
                    }

                    break;


                case ("2"):
                    try {
                        List<Book> booksWithOrderOfYearOfPublishment = libraryWithBooks.stream()
                                .sorted(comparing(Book::getYearOfPublishment).reversed())
                                .collect(Collectors.toList());

                        booksWithOrderOfYearOfPublishment.forEach(System.out::println);
                    } catch (Exception e){
                        System.out.println("Przepraszamy wystąpił nieoczekiwany błąd: " + e.getMessage());
                        e.getStackTrace();
                    }

                    break;


                case ("3"):
                    String statementForWhileInCase3 = "y";
                    while (statementForWhileInCase3.equals("y")) {

                        System.out.println("Wybierz jedną z kategorii: DOCKER, PYTHON, JAVA, MACHINE LEARNING, ARCHITEKTURA");
                        String operatorCategory = scanner.nextLine().toUpperCase().trim().replace(" ", "");
                        int numberForPass = 0;

                        for (Category valueOfCategory : Category.values()) {
                            if ((operatorCategory.matches(String.valueOf(valueOfCategory))) || operatorCategory.equals("MACHINELEARNING")) {
                                numberForPass = 1;
                                break;
                            }
                        }

                        System.out.println();

                        if (numberForPass == 1) {
                            if (operatorCategory.equals("MACHINELEARNING")) {
                                System.out.println("To wszystkie książki z kategorii MACHINE LEARNING");
                                System.out.println();
                                libraryWithBooks.stream()
                                        .filter(book -> book.getCategory().equals(Category.MACHINE_LEARNING))
                                        .forEach(System.out::println);
                            } else {
                                operatorCategory = operatorCategory.replaceAll(" ", "");
                                System.out.println("To wszystkie książki z kategorii " + operatorCategory + ": ");
                                System.out.println();
                                for (Book bookWithCategory : libraryWithBooks) {
                                    if (bookWithCategory.getCategory().equals(Category.valueOf(operatorCategory))) {
                                        System.out.println(bookWithCategory);
                                    }
                                }
                            }
                            System.out.println();
                            statementForWhileInCase3 = "n";
                        } else {
                            System.out.println("nie znaleziono wybranej kategorii. Przekuać jeszcze raz? y/n");
                            statementForWhileInCase3 = scanner.nextLine();
                        }
                    }
                    break;


                case ("4"):
                    String statementForWhileInCase4 = "y";
                    while (statementForWhileInCase4.equals("y")) {

                        System.out.println("Podaj numer ISBN z myślnikami np: 978-83-283-2480-0");
                        String operatorISBN = scanner.nextLine().replaceAll(" ", "").toUpperCase();
                        System.out.println("Podany number ISBN: " + operatorISBN);
                        System.out.println();

                        if (libraryWithBooks.stream()
                                .anyMatch(book -> Boolean.parseBoolean(book.getISBN()))){

                            System.out.println("Książka z podanym numerem ISBN to: ");

                            List<Book> bookWithCorrectISBN = libraryWithBooks.stream()
                                    .filter(book -> book.getISBN().equals(operatorISBN))
                                    .collect(Collectors.toList());

                            System.out.println(bookWithCorrectISBN);
                            System.out.println();
                            statementForWhileInCase4 = "n";
                        } else {
                            System.out.println("W zbiorze nie ma książki z podanym numerem ISBN");
                            System.out.println("Chcesz spróbować ponownie? y/n");
                            statementForWhileInCase4 = scanner.nextLine();
                        }
                    }
                    break;

                case ("5"):
                    String statementForWhileInCase5 = "y";
                    while (statementForWhileInCase5.equals("y")) {

                        System.out.println("Podaj Imię oraz nazwisko autora np: Joshua Bloch ");

                        String operatorAuthor = scanner.nextLine();
                        System.out.println("Podano autora: " + operatorAuthor);

                        System.out.println();
                        System.out.println("Wszystkie książki z podanym autorem: ");

                        int numberOfBooksWithUserAuthor = 0;

                        for (Book book : libraryWithBooks) {
                            for (String authorsOfBook : book.getAuthor().getNamesOfAuthorsList()) {
                                if (authorsOfBook.toLowerCase().contains(operatorAuthor.toLowerCase())) {
                                    numberOfBooksWithUserAuthor++;
                                    System.out.println(book);
                                }
                            }
                        }
                        if (numberOfBooksWithUserAuthor == 0) {
                            System.out.println("Nie znaleziono książek z wybranym autorem");
                            System.out.println("Chcesz wyszukać jeszcze raz? y/n");
                            statementForWhileInCase5 = scanner.nextLine();
                        } else {
                            statementForWhileInCase5 = "n";
                        }
                    }
                    break;

                default:
                    System.out.println("Wybierz opcje od 1 do 5");
                    System.out.println();
            }

            System.out.println("Chcesz wyjsc? y/n");
            statementForWhile = scanner.nextLine().toLowerCase().trim();


        }





    }
}

/*
    Book App
    Stworzyć aplikacje do przetwarzania danych o książkach. Aplikacja powinna posiadać menu
        obsługiwane przez wiersz poleceń. W momencie uruchomienia aplikacji należy podać ścieżke do
        pliku z danymi. Dane ze wskazanego pliku należy załadować do pamięci.
        Menu
        W menu powinny pojawić się opcje:
        1. Wypisz książkę z największą liczbą stron
        2. Posortuj i wypisz książki według roku wydania (od najnowszej)
        3. Wypisz książki w zależności od podanego typu
        4. Wypisz książkę w zależności od podanego numeru ISBN
        5. Wypisz książki w zależności od podanego autora
        6. Zapisz do wskazanego pliku posortowane alfabetycznie (według tytułu) książki
        Format
        Dane posiadają format:
        Tytul;Autor;Rok wydania;Ilosc stron;Wydawnictwo;Rodzaj;ISBN
        Wymagania techniczne
        • Java 8
        • Maven
        • Testy (dla głównych funkcjonalności) - JUnit i AssertJ
        • Pokrycie kodu testami (CodeCoverage)
        • CheckStyle
        • Kod po angielsku
        • Obsługa błędów (podanie złego numeru w menu, złe dane)
        • Musi działać jako osobny jar
*/
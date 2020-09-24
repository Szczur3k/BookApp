import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.*;

public class Runner {
    public static void main(String[] args) {

        BooksFromFile booksFromFile = new BooksFromFile();
        booksFromFile.readBooksFromFile();
        List<Book> libraryWithBooks = BooksFromFile.bookInFile;

        String operatorMenu;
        String statementForWhile = "n";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w księgarni");

        while (statementForWhile.equals("n")) {

            System.out.println("Wybierz jedną z poniżej dostępnych opcji poprzez wpisanie w konsolę wybranej liczby");
            System.out.println("1. Wypisz książkę z największą liczbą stron");
            System.out.println("2. Posortuj i wypisz książki według roku wydania (od najnowszej)");
            System.out.println("3. Wypisz książki w zależności od podanego typu");
            System.out.println("4. Wypisz książkę w zależności od podanego numeru ISBN");
            System.out.println("5. Wypisz książki w zależności od podanego autora");
            System.out.println();
            System.out.print("Twój wybór to: ");
            operatorMenu = scanner.nextLine();

            switch (operatorMenu) {
                case ("1"):
                    List<Book> maxPages = libraryWithBooks.stream()
                            .sorted(comparing(Book::getNumberOfPages).reversed())
                            .collect(Collectors.toList());

                    System.out.println(maxPages.get(0));
                    break;


                case ("2"):
                    List<Book> booksWithOrderOfYearOfPublishment = libraryWithBooks.stream()
                            .sorted(comparing(Book::getYearOfPublishment).reversed())
                            .collect(Collectors.toList());

                    booksWithOrderOfYearOfPublishment.forEach(System.out::println);
                    break;


                case ("3"):
                    System.out.println("Wybierz jedną z kategorii: DOCKER, PYTHON, JAVA, MACHINE LEARNING, ARCHITEKTURA");
                    String operatorCategory = scanner.nextLine().toUpperCase();

                    System.out.println();

                    if (operatorCategory.equals("MACHINELEARNING") || operatorCategory.equals("MACHINE LEARNING")) {
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

                    break;


                case ("4"):
                    System.out.println("Podaj numer ISBN z myślnikami np: 978-83-283-2480-0");
                    String operatorISBN = scanner.nextLine().replaceAll(" ", "").toUpperCase();
                    System.out.println("Podaj ISBN: " + operatorISBN);
                    System.out.println();
                    System.out.println("Książka z podanym numerem ISBN to: ");
                    List<Book> bookWithCorrectISBN = libraryWithBooks.stream()
                            .filter(book -> book.getISBN().equals(operatorISBN))
                            .collect(Collectors.toList());

                    System.out.println(bookWithCorrectISBN);
                    System.out.println();
                    break;

                case ("5"):
                    System.out.println("Podaj Imię oraz nazwisko autora np: Joshua Bloch ");
                    String operatorAuthor = scanner.nextLine();
                    System.out.println("Podaj autora: " + operatorAuthor);

                    System.out.println();
                    System.out.println("Wszystkie książki z podanym autorem: ");

                    for (Book book : libraryWithBooks) {
                        for (String authorsOfBook : book.getAuthor().getNamesOfAuthorsList()) {
                            if (authorsOfBook.toLowerCase().contains(operatorAuthor.toLowerCase())) {
                                System.out.println(book);
                            }
                        }
                    }
                    break;
            }

            System.out.println("Chcesz wyjsc? y/n");
            statementForWhile = scanner.nextLine();


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


/*
Dane:

Docker. Projektowanie i wdrazanie aplikacji;Jaroslaw Krochmalski;2018;224;Packt;Docker;978-83-283-3534-9
Docker dla praktykow;Russ McKendrick, Pethuru Raj;2017;248;Helion;Docker;978-83-283-3972-9
Docker. Programowanie aplikacji dla zaawansowanych;Russ McKendrick, Scott Gallagher;2018;320;Packt;Docker;978-83-283-4308-5
Python. Uczenie maszynowe;Sebastian Raschka;2014;416;Packt;Python;978-83-283-3613-1
Nowoczesne receptury w Javie;Ken Kousen;2018;264;Packt;Java;978-83-283-4073-2
Java. Podstawy. Wydanie X;Cay S. Horstmann;2016;872;Helion;Java;978-83-283-2480-0
Java. Efektywne programowanie;Joshua Bloch;2018;408;OReailly;Java;978-83-283-4576-8
Uczenie maszynowe;Aurelien Geron;2018;528;OReailly;Machine Learning;978-83-283-4373-3
Efektywny Python;Brett Slatkin;2015;232;Helion;Python;978-83-283-1540-2
Czysta architektura;Robert C. Martin;2018;386;Helion;Architektura;978-83-283-4225-5
 */
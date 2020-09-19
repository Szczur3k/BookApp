import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.*;

public class Runner {
    public static void main(String[] args) {

        List<Book> libraryWithBooks = new ArrayList<>();
        libraryWithBooks.add(new Book(new Book.BookBuilder("Docker. Projektowanie i wdrażanie aplikacji", new Author(Collections.singletonList("Jarosław Krochmalski")), 224, Category.DOCKER, "Packt", 2018, "978-83-283-3534-9")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Docker dla praktyków", new Author(Arrays.asList("Russ McKendrick", "Pethuru Raj")), 248, Category.DOCKER, "Helion", 2017, "978-83-283-3972-9")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Docker. Programowanie aplikacji dla zaawansowanych", new Author(Arrays.asList("Russ McKendrick", "Scott Gallagher")), 320, Category.DOCKER, "Packt", 2018, "978-83-283-4308-5")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Python. Uczenie maszynowe", new Author(Collections.singletonList("Sebastian Raschka")), 416, Category.PYTHON, "Packt", 2014, "978-83-283-3613-1")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Nowoczesne receptury w Javie", new Author(Collections.singletonList("Ken Kousen")), 264, Category.JAVA, "Packt", 2018, "978-83-283-4073-2")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Java. Podstawy. Wydanie X", new Author(Collections.singletonList("Cay S. Horstmann")), 872, Category.JAVA, "Helion", 2016, "978-83-283-2480-0")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Java. Efektywne programowanie", new Author(Collections.singletonList("Joshua Bloch")), 408, Category.JAVA, "OReailly", 2018, "978-83-283-4576-8")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Uczenie maszynowe", new Author(Collections.singletonList("Aurelien Geron")), 528, Category.MACHINE_LEARNING, "OReailly", 2018, "978-83-283-4373-3")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Efektywny Python", new Author(Collections.singletonList("Brett Slatkin")), 232, Category.PYTHON, "Helion", 2015, "978-83-283-1540-2")));
        libraryWithBooks.add(new Book(new Book.BookBuilder("Czysta architektura", new Author(Collections.singletonList("Robert C. Martin")), 386, Category.ARCHITEKTURA, "Helion", 2018, "978-83-283-4225-5")));

        String operatorMenu;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w księgarni");
        System.out.println("Wybierz jedną z poniżej dostępnych opcji poprzez wpisanie w konsolę wybranej liczby");
        System.out.println("1. Wypisz książkę z największą liczbą stron");
        System.out.println("2. Posortuj i wypisz książki według roku wydania (od najnowszej)");
        System.out.println("3. Wypisz książki w zależności od podanego typu");
        System.out.println("4. Wypisz książkę w zależności od podanego numeru ISBN");
        System.out.println("5. Wypisz książki w zależności od podanego autora");
        System.out.println();
        System.out.print("Twój wybór to: ");
        operatorMenu = scanner.nextLine();

        switch (operatorMenu){
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

                System.out.println(booksWithOrderOfYearOfPublishment);
                break;


            case ("3"):
                System.out.println("Wybierz jedną z kategorii: DOCKER, PYTHON, JAVA, MACHINE LEARNING, ARCHITEKTURA");
                String operatorCategory = scanner.nextLine().toUpperCase();

                System.out.println();

                if (operatorCategory.equals("MACHINELEARNING") || operatorCategory.equals("MACHINE LEARNING")){
                    System.out.println("To wszystkie książki z kategorii MACHINE LEARNING");
                    System.out.println();
                    libraryWithBooks.stream()
                            .filter(book -> book.getCategory().equals(Category.MACHINE_LEARNING))
                            .forEach(System.out::println);
                }
                else {
                    operatorCategory = operatorCategory.replaceAll(" ", "");

                    System.out.println("To wszystkie książki z kategorii " + operatorCategory + ": ");
                    System.out.println();
                    for (Book libraryWithBook : libraryWithBooks) {
                        if (libraryWithBook.getCategory().equals(Category.valueOf(operatorCategory))) {
                            System.out.println(libraryWithBook);
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
                        if (authorsOfBook.toLowerCase().contains(operatorAuthor.toLowerCase())){
                            System.out.println(book);
                        }
                    }
                }

                break;




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
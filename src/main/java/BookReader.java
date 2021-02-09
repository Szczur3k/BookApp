import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class BookReader {

    List<Book> allBooksInLibrary;
    String menuChoice;
    String runUntilUserSaysY = "n";
    Scanner scanner = new Scanner(System.in);

    public void bookChoice() {
        while (runUntilUserSaysY.equals("n")) {
            menuText();
            getBooksFromFile();
            menuChoice = scanner.nextLine();
            runMenuChoice();
        }
    }

    private void runMenuChoice() {
        switch (menuChoice) {
            case ("1"):
                getBookWithMaxPages();
                break;

            case ("2"):
                sortAndShowBooksWithYearOfPublishment();
                break;

            case ("3"):
                showBooksByGivenCategory();
                break;

            case ("4"):
                showBooksByGivenISBNNumber();
                break;

            case ("5"):
                showBooksByGivenAuthor();
                break;

            default:
                System.out.println("Wybierz opcje od 1 do 5");
                System.out.println();
        }

        System.out.println("Chcesz wyjsc? y/n");
        runUntilUserSaysY = scanner.nextLine().toLowerCase().trim();
    }

    private void showBooksByGivenAuthor() {
        String runUntilUserSaysYInCase5 = "y";
        while (runUntilUserSaysYInCase5.equals("y")) {

            System.out.println("Podaj Imię oraz nazwisko autora np: Joshua Bloch ");

            String operatorAuthor = scanner.nextLine();
            System.out.println("Podano autora: " + operatorAuthor);

            System.out.println();
            System.out.println("Wszystkie książki z podanym autorem: ");

            int numberOfBooksWithUserAuthor = 0;

            for (Book book : allBooksInLibrary) {
                for (String authorsOfBook : book.getAuthor().getNamesOfAllAuthors()) {
                    if (authorsOfBook.toLowerCase().contains(operatorAuthor.toLowerCase())) {
                        numberOfBooksWithUserAuthor++;
                        System.out.println(book);
                    }
                }
            }
            if (numberOfBooksWithUserAuthor == 0) {
                System.out.println("Nie znaleziono książek z wybranym autorem");
                System.out.println("Chcesz wyszukać jeszcze raz? y/n");
                runUntilUserSaysYInCase5 = scanner.nextLine();
            } else {
                runUntilUserSaysYInCase5 = "n";
            }
        }
    }

    private void showBooksByGivenISBNNumber() {
        String statementForWhileInCase4 = "y";
        while (statementForWhileInCase4.equals("y")) {

            System.out.println("Podaj numer ISBN z myślnikami np: 978-83-283-2480-0");
            String operatorISBN = scanner.nextLine().replaceAll(" ", "").toUpperCase();
            System.out.println("Podany number ISBN: " + operatorISBN);
            System.out.println();

            if (allBooksInLibrary.stream()
                    .anyMatch(book -> Boolean.parseBoolean(book.getISBN()))) {

                System.out.println("Książka z podanym numerem ISBN to: ");

                List<Book> bookWithCorrectISBN = allBooksInLibrary.stream()
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
    }

    private void showBooksByGivenCategory() {
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
                    allBooksInLibrary.stream()
                            .filter(book -> book.getCategory().equals(Category.MACHINE_LEARNING))
                            .forEach(System.out::println);
                } else {
                    operatorCategory = operatorCategory.replaceAll(" ", "");
                    System.out.println("To wszystkie książki z kategorii " + operatorCategory + ": ");
                    System.out.println();
                    for (Book bookWithCategory : allBooksInLibrary) {
                        if (bookWithCategory.getCategory().equals(Category.valueOf(operatorCategory))) {
                            System.out.println(bookWithCategory);
                        }
                    }
                }
                System.out.println();
                statementForWhileInCase3 = "n";
            } else {
                System.out.println("nie znaleziono wybranej kategorii. Przeszukać jeszcze raz? y/n");
                statementForWhileInCase3 = scanner.nextLine();
            }
        }
    }

    private void sortAndShowBooksWithYearOfPublishment() {
        try {
            List<Book> sortedBooksWithOrderOfYearOfPublishment = allBooksInLibrary.stream()
                    .sorted(comparing(Book::getYearOfPublishment).reversed())
                    .collect(Collectors.toList());

            sortedBooksWithOrderOfYearOfPublishment.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Przepraszamy wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.getStackTrace();
        }
    }

    private void getBookWithMaxPages() {
        try {
            List<Book> sortedBooksWithMaxPages = allBooksInLibrary.stream()
                    .sorted(comparing(Book::getNumberOfPages).reversed())
                    .collect(Collectors.toList());

            System.out.println(sortedBooksWithMaxPages.get(0));
        } catch (Exception e) {
            System.out.println("Przepraszamy, wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.getStackTrace();
        }
    }


    //Making Instance of readingBooksFromFile and using method for reading it. In the method .redBooksFromFile() we are adding Books to the list which is called here (beacuse of static field)
    private void getBooksFromFile() {
        BooksFromFile booksFromFile = new BooksFromFile();
        booksFromFile.readBooksFromFile();
        allBooksInLibrary = BooksFromFile.booksList;
    }

    private void menuText() {
        System.out.println("Witaj w księgarni");
        System.out.println("Wybierz jedną z poniżej dostępnych opcji poprzez wpisanie w konsolę wybranej liczby");
        System.out.println("1. Wypisz książkę z największą liczbą stron");
        System.out.println("2. Posortuj i wypisz książki według roku wydania (od najnowszej)");
        System.out.println("3. Wypisz książki w zależności od podanego typu");
        System.out.println("4. Wypisz książkę w zależności od podanego numeru ISBN");
        System.out.println("5. Wypisz książki w zależności od podanego autora");
        System.out.println();
        System.out.print("Twój wybór to: ");
    }


}

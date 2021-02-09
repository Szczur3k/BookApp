import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class BooksFromFile {

    //given instance of a path. Static List is for Runner. We dont need to change it or making new instance of it.
    String booksFilePath = "src/main/resources/dane.txt";
    static List<Book> booksList = new ArrayList<>();


    public void readBooksFromFile() {

        //Loading file from path and reading ONE LINE of it
        try (BufferedReader fileReader = new BufferedReader(new FileReader(booksFilePath))) {
            String textInLine = fileReader.readLine();

            //Loop for reading line and split it with ";" sign. Then each of text before sign set to the Book.builder. On the end of Loop we are building Book.
            while (textInLine != null) {
                textInLine = fileReader.readLine();
                String[] splitetTextInFile = textInLine.split(";");
                saveBookFromFile(splitetTextInFile);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    private void saveBookFromFile(String[] splitetTextInFile) {
        Book.BookBuilder bookBuilder = new Book.BookBuilder();

        if (bookBuilder.getTitleOfBook() == null) {
            bookBuilder.setTitleOfBook(splitetTextInFile[0]);
        }

        if (bookBuilder.getAuthor() == null) {
            if (splitetTextInFile[1].contains(",")) {
                String[] authorsInFile = splitetTextInFile[1].split(",");
                bookBuilder.setAuthor(new Author(Arrays.asList(authorsInFile)));
            } else {
                bookBuilder.setAuthor(new Author(Collections.singletonList(splitetTextInFile[1])));
            }
        }

        if (bookBuilder.getYearOfPublishment() == 0) {
            bookBuilder.setYearOfPublishment(Integer.parseInt(splitetTextInFile[2]));
        }

        if (bookBuilder.getNumberOfPages() == 0) {
            bookBuilder.setNumberOfPages(Integer.parseInt(splitetTextInFile[3]));
        }

        if (bookBuilder.getPublisher() == null) {
            bookBuilder.setPublisher(splitetTextInFile[4]);
        }

        if (bookBuilder.getCategory() == null) {
            bookBuilder.setCategory(Category.valueOf(splitetTextInFile[5].toUpperCase().replace(" ", "_")));
        }

        if (bookBuilder.getISBN() == null) {
            bookBuilder.setISBN(splitetTextInFile[6]);
        }

        Book book = new Book(bookBuilder);
        booksList.add(book);
    }

}

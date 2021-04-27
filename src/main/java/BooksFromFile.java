import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class BooksFromFile {

    //given instance of a path. Static List is for Runner. We dont need to change it or making new instance of it.
    String booksFilePath = "src/main/resources/dane.txt";
    static List<Book> booksList = new ArrayList<>();


    public void readBooksFromFile(String booksFilePath) {

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
        Book book = new Book();

        if (book.getTitleOfBook() == null) {
            book.setTitleOfBook(splitetTextInFile[0]);
        }

        if (book.getAuthor() == null) {
            if (splitetTextInFile[1].contains(",")) {
                String[] authorsInFile = splitetTextInFile[1].split(",");
                book.setAuthor(new Author(Arrays.asList(authorsInFile)));
            } else {
                book.setAuthor(new Author(Collections.singletonList(splitetTextInFile[1])));
            }
        }

        if (book.getYearOfPublishment() == 0) {
            book.setYearOfPublishment(Integer.parseInt(splitetTextInFile[2]));
        }

        if (book.getNumberOfPages() == 0) {
            book.setNumberOfPages(Integer.parseInt(splitetTextInFile[3]));
        }

        if (book.getPublisher() == null) {
            book.setPublisher(splitetTextInFile[4]);
        }

        if (book.getCategory() == null) {
            book.setCategory(Category.valueOf(splitetTextInFile[5].toUpperCase().replace(" ", "_")));
        }

        if (book.getISBN() == null) {
            book.setISBN(splitetTextInFile[6]);
        }

        booksList.add(book);
    }

}

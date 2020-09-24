import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class BooksFromFile {

    String filePath = "src/main/resources/dane.txt";
    static List<Book> bookInFile = new ArrayList<>();


    public void readBooksFromFile() {

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String textLine = fileReader.readLine();

            while (textLine != null) {
                textLine = fileReader.readLine();
                Book.BookBuilder bookBuilder = new Book.BookBuilder();
                String[] textInArray = textLine.split(";");

                if (bookBuilder.getTitleOfBook()==null) {
                    bookBuilder.setTitleOfBook(textInArray[0]);
                }

                if (bookBuilder.getAuthor()==null) {
                    if (textInArray[1].contains(",")) {
                        String[] authors = textInArray[1].split(",");
                        bookBuilder.setAuthor(new Author(Arrays.asList(authors)));
                    }
                    else {
                        bookBuilder.setAuthor(new Author(Collections.singletonList(textInArray[1])));
                    }
                }

                if (bookBuilder.getYearOfPublishment()==0) {
                    bookBuilder.setYearOfPublishment(Integer.parseInt(textInArray[2]));
                }

                if (bookBuilder.getNumberOfPages()==0){
                    bookBuilder.setNumberOfPages(Integer.parseInt(textInArray[3]));
                }

                if (bookBuilder.getPublisher()==null){
                    bookBuilder.setPublisher(textInArray[4]);
                }

                if (bookBuilder.getCategory()==null){
                    bookBuilder.setCategory(Category.valueOf(textInArray[5].toUpperCase().replace(" ", "_")));
                }

                if (bookBuilder.getISBN()==null){
                    bookBuilder.setISBN(textInArray[6]);
                }

                Book book = new Book(bookBuilder);
                bookInFile.add(book);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void showBooksFromFile(){
        bookInFile.forEach(System.out::println);
    }


}

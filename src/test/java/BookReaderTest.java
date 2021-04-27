import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookReaderTest {

    static final String SRC = "src/main/resources/dane.txt";

    @Test
    public void shouldHasExactSizeAsBookFromFileList(){

        //Given
        BookReader bookReader = new BookReader();
        BooksFromFile booksFromFile = new BooksFromFile();

        //When
        booksFromFile.readBooksFromFile(SRC);
        bookReader.bookChoice();

        //Then
        assertThat(bookReader.allBooksInLibrary).hasSize(BooksFromFile.booksList.size());

    }

}
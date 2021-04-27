import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BooksFromFileTest {

    static final String SRC = "src/main/resources/dane.txt";

    @Test
    public void shouldReturnNotNullWhenReadBookFromFile(){

        //Given
        BooksFromFile booksFromFile = new BooksFromFile();

        //When
        booksFromFile.readBooksFromFile(SRC);

        //Then
        assertThat(BooksFromFile.booksList).isNotNull();

    }

    @Test
    public void shouldHasExactSizeInList(){

        //Given
        BooksFromFile booksFromFile = new BooksFromFile();

        //When
        booksFromFile.readBooksFromFile(SRC);

        assertThat(BooksFromFile.booksList).hasSize(9);
    }

}
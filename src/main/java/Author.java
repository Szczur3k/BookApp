import java.util.Arrays;
import java.util.List;

public class Author {
    private List<String> authors;

    public Author(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getNamesOfAuthorsList() {
        return authors;
    }

    @Override
    public String toString() {
        return "List of authors: '" + authors;
    }

}

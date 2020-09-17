import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Author {
    private String nameAndSurnameOfAuthor;
    private List<String> authors;

    public Author(String nameAndSurnameOfAuthor) {
        this.nameAndSurnameOfAuthor = nameAndSurnameOfAuthor;
    }

    public Author(String... nameAndSurnameOfAuthor) {
        authors = Arrays.asList(nameAndSurnameOfAuthor.clone());
    }

    public String getNameOfAuthor() {
        return nameAndSurnameOfAuthor;
    }

    public List<String> getNameOfAuthorsList() {
        return authors;
    }


    @Override
    public String toString() {
        if (nameAndSurnameOfAuthor != null) {
            return "Author: '" + nameAndSurnameOfAuthor;
        }
        else
            return "Authors: '" + authors;
    }

}

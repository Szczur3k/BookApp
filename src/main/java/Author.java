public class Author {
    private String nameAndSurnameOfAuthor;
    private String[] authors;

    public Author(String nameAndSurnameOfAuthor) {
        this.nameAndSurnameOfAuthor = nameAndSurnameOfAuthor;
    }

    public Author(String ... nameAndSurnameOfAuthor){
        this.authors = nameAndSurnameOfAuthor;
    }

    public String getNameOfAuthor() {
        return nameAndSurnameOfAuthor;
    }
}

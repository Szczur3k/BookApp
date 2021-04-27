public class Book {
    private String titleOfBook;
    private Author author;
    private int yearOfPublishment;
    private int numberOfPages;
    private String publisher;
    private Category category;
    private String ISBN;

    public String getTitleOfBook() {
        return titleOfBook;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYearOfPublishment() {
        return yearOfPublishment;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public Category getCategory() {
        return category;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setTitleOfBook(String titleOfBook) {
        this.titleOfBook = titleOfBook;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setYearOfPublishment(int yearOfPublishment) {
        this.yearOfPublishment = yearOfPublishment;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "TitleOfBook: '" + titleOfBook + '\'' + '\n' +
                "Author: '" + author.getNamesOfAllAuthors() + '\'' + '\n' +
                "Year of publishment: '" + yearOfPublishment + '\'' + '\n' +
                "Number of pages: '" + numberOfPages + '\'' + '\n' +
                "Publisher: '" + publisher + '\'' + '\n' +
                "Category: '" + category + '\'' + '\n' +
                "ISBN: '" + ISBN + '\'' + '\n';

    }
}

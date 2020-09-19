public class Book {
    private String titleOfBook;
    private Author author;
    private int yearOfPublishment;
    private int numberOfPages;
    private String publisher;
    private Category category;
    private String ISBN;

    //W sumie builder nie jest potrzebny, ale nie jestem pewien czy wszystko powinno być niezmienne, więc zostawię w razie czego
    public Book(final BookBuilder builder){
        this.titleOfBook = builder.titleOfBook;
        this.author = builder.author;
        this.yearOfPublishment = builder.yearOfPublishment;
        this.numberOfPages = builder.numberOfPages;
        this.publisher = builder.publisher;
        this.category = builder.category;
        this.ISBN = builder.ISBN;
    }

    static class BookBuilder{
        private final String titleOfBook;
        private final Author author;
        private final int numberOfPages;
        private final Category category;
        private final String publisher;
        private final int yearOfPublishment;
        private final String ISBN;


        BookBuilder(String titleOfBook, Author author, int numberOfPages, Category category, String publisher, int yearOfPublishment, String isbn) {
            this.titleOfBook = titleOfBook;
            this.author = author;
            this.numberOfPages = numberOfPages;
            this.category = category;
            this.publisher = publisher;
            this.yearOfPublishment = yearOfPublishment;
            ISBN = isbn;
        }

        public Book build(){
            return new Book(this);
        }
    }


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

    @Override
    public String toString() {
        return "TitleOfBook: '" + titleOfBook + '\'' + '\n' +
                "Author: '" + author.getNamesOfAuthorsList() + '\'' + '\n' +
                "Year of publishment: '" + yearOfPublishment + '\'' + '\n' +
                "Number of pages: '" + numberOfPages + '\'' + '\n' +
                "Publisher: '" + publisher + '\'' + '\n' +
                "Category: '" + category  + '\'' + '\n' +
                "ISBN: '" + ISBN + '\'' + '\n';

    }
}

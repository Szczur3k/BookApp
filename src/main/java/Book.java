public class Book {
    private String titleOfBook;
    private Author author;
    private int yearOfPublishment;
    private int numberOfPages;
    private String publisher;
    private Category category;
    private String ISBN;

    //W sumie builder nie jest potrzebny, ale nie jestem pewien czy wszystko powinno być niezmienne, więc zostawię w razie czego
    public Book(final BookBuilder builder) {
        this.titleOfBook = builder.titleOfBook;
        this.author = builder.author;
        this.yearOfPublishment = builder.yearOfPublishment;
        this.numberOfPages = builder.numberOfPages;
        this.publisher = builder.publisher;
        this.category = builder.category;
        this.ISBN = builder.ISBN;
    }

    static class BookBuilder {
        private String titleOfBook;
        private Author author;
        private int numberOfPages;
        private Category category;
        private String publisher;
        private int yearOfPublishment;
        private String ISBN;


        BookBuilder(String titleOfBook, Author author, int numberOfPages, Category category, String publisher, int yearOfPublishment, String isbn) {
            this.titleOfBook = titleOfBook;
            this.author = author;
            this.numberOfPages = numberOfPages;
            this.category = category;
            this.publisher = publisher;
            this.yearOfPublishment = yearOfPublishment;
            ISBN = isbn;
        }

        BookBuilder() {
        }

        public void setTitleOfBook(String titleOfBook) {
            this.titleOfBook = titleOfBook;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public void setNumberOfPages(int numberOfPages) {
            this.numberOfPages = numberOfPages;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public void setYearOfPublishment(int yearOfPublishment) {
            this.yearOfPublishment = yearOfPublishment;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }

        public String getTitleOfBook() {
            return titleOfBook;
        }

        public Author getAuthor() {
            return author;
        }

        public int getNumberOfPages() {
            return numberOfPages;
        }

        public Category getCategory() {
            return category;
        }

        public String getPublisher() {
            return publisher;
        }

        public int getYearOfPublishment() {
            return yearOfPublishment;
        }

        public String getISBN() {
            return ISBN;
        }

        public Book build() {
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
                "Author: '" + author.getNamesOfAllAuthors() + '\'' + '\n' +
                "Year of publishment: '" + yearOfPublishment + '\'' + '\n' +
                "Number of pages: '" + numberOfPages + '\'' + '\n' +
                "Publisher: '" + publisher + '\'' + '\n' +
                "Category: '" + category + '\'' + '\n' +
                "ISBN: '" + ISBN + '\'' + '\n';

    }
}

package Model;

/**
 *
 * @author nAdA
 */
public class Book {

    private String title;
    private String author;
    private String category;
    private String isbn;
    private String publishDate;
    private String pageCount;
    private String copyCount;
    private String language;
    private String publisher;
    private String BookImagePath;

    public Book(String BookImagePath, String category, String language, String title, String author, String isbn, String publishDate, String pageCount, String copyCount, String publisher) {
        this.BookImagePath = BookImagePath;
        this.category = category;
        this.language = language;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
        this.copyCount = copyCount;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getCopyCount() {
        return copyCount;
    }

    public void setCopyCount(String copyCount) {
        this.copyCount = copyCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookImagePath() {
        return BookImagePath;
    }

    public void setBookImagePath(String BookImagePath) {
        this.BookImagePath = BookImagePath;
    }

}

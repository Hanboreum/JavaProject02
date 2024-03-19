package kr.book.search;

public class Book {
    private String title;
    private String authors;
    private String thumbnail;

    public Book(){}

    public Book(String title, String authors, String thumbnail) {
        this.title = title;
        this.authors = authors;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}

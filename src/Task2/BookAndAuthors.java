package Task2;

public class BookAndAuthors {
    public BookAndAuthors(int id_bookAndAuthors, int id_book, int id_author) {
        this.id_bookAndAuthors = id_bookAndAuthors;
        this.id_book = id_book;
        this.id_author = id_author;
    }

    int id_bookAndAuthors;
    int id_book;
    int id_author;

    @Override
    public String toString() {
        return "Task2.BookAndAuthors{" +
                "id_bookAndAuthors=" + id_bookAndAuthors +
                ", id_book=" + id_book +
                ", id_author=" + id_author +
                '}';
    }
}

package Task2;

public class Book {
    public Book(int id_book, String nameBook, int totalNumb, int numbCheckedOutForVisitors, int remainderNumb) {
        this.id_book = id_book;
        this.nameBook = nameBook;
        TotalNumb = totalNumb;
        NumbCheckedOutForVisitors = numbCheckedOutForVisitors;
        RemainderNumb = remainderNumb;
    }

    int id_book;
    String nameBook;
    int TotalNumb;
   int NumbCheckedOutForVisitors;
   int RemainderNumb;

    @Override
    public String toString() {
        return "Task2.Book{" +
                "id_book=" + id_book +
                ", nameBook='" + nameBook + '\'' +
                ", TotalNumb=" + TotalNumb +
                ", NumbCheckedOutForVisitors=" + NumbCheckedOutForVisitors +
                ", RemainderNumb=" + RemainderNumb +
                '}';
    }
}

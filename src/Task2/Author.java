package Task2;

public class Author {
    int id_author;
    String firstName;
    String lastName;

    public Author(int id_author, String firstName, String lastName) {
        this.id_author = id_author;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Task2.Author{" +
                "id_author=" + id_author +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

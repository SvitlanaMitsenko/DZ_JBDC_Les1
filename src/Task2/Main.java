package Task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        registerDriver();
        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        Statement statement4 = null;
        ArrayList<Author> authors = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<BookAndAuthors> bookAndAuthors = new ArrayList<>();


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            BufferedReader bf;
            try{
                bf = new BufferedReader(new FileReader("resources/LibraryDB.txt" ));
                String line = bf.readLine();
                while(line != null){
                    System.out.println(line);
                    statement.addBatch(line);
                    line = bf.readLine();
                }

        }catch (IOException e){
                e.printStackTrace();
            }
            statement.executeBatch();

            statement2 = connection.createStatement();
            ResultSet resultSet = statement2.executeQuery("SELECT * FROM Author");

            statement3 = connection.createStatement();
            ResultSet resultSet2 = statement3.executeQuery("SELECT * FROM Book");

            statement4 = connection.createStatement();
            ResultSet resultSet3= statement4.executeQuery("SELECT * FROM BookAndAuthors");


            while (resultSet.next()) {
                authors.add(new Author(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3)));
            }
            while (resultSet2.next()) {
                books.add(new Book(resultSet2.getInt(1), resultSet2.getString("nameBook"),resultSet2.getInt(3), resultSet2.getInt(4), resultSet2.getInt(5)));
            }
            while (resultSet3.next()) {
                bookAndAuthors.add(new BookAndAuthors(resultSet3.getInt(1), resultSet3.getInt(2),resultSet3.getInt(3)));
            }

           boolean closed = statement.isClosed();
            System.out.println(closed);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                statement2.close();
                statement3.close();
                statement4.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println("<========АВТОРЫ========>");
        for(Author a: authors){
            System.out.println(a);
        }
        System.out.println("<========КНИГИ========>");
        books.forEach((item)-> System.out.println(item));
        System.out.println("<========КНИГИ и АВТОРЫ========>");
        bookAndAuthors.forEach((item)-> System.out.println(item));
    }
    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

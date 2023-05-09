package Task6;

import Task3_5.Employee;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        registerDriver();
        Connection connection = null;
        Statement statement1 = null;
        Statement statement2 = null;
        PreparedStatement statement3 = null;
        PreparedStatement statement4 = null;
        Statement statement5 = null;
        Statement statement6 = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement1 = connection.createStatement();
            statement1.execute(" CREATE table goods \n" +
                    "    ( id INT primary key auto_increment NOT NULL,\n" +
                    "      name NVARCHAR(20),\n" +
                    "      price int2\n" +
                    "     );");
            statement2 = connection.createStatement();
            int res2 = statement2.executeUpdate("INSERT INTO goods\n" +
                    "(name, price)\n" +
                    "VALUES\n" +
                    "('юбка',  800),\n" +
                    "('рубашка', 700),\n" +
                    "('платье',  2000),\n" +
                    "('сумка',  3000),\n" +
                    "('сапоги',  5000); ");
            if (res2 > 0 ) System.out.println("Успешно добавленo " + res2 + " записей");

            statement5 = connection.createStatement();
            ResultSet resultSet5 = statement5.executeQuery("SELECT * FROM goods");
            System.out.println("<=====Список товаров до обновления=====>");
            while (resultSet5.next()){
                System.out.println(resultSet5.getInt(1)+ " " + resultSet5.getString(2) + " " +  resultSet5.getInt(3));
            }


            int id = 2;
            statement3 = connection.prepareStatement("DELETE FROM goods WHERE id = ?");
            statement3.setInt(1, id);
            id = 3;
            String name = "платье";
            int res3 = statement3.executeUpdate();
            if ( res3 > 0 ) System.out.println("\nУспешно удалено");


            statement4 = connection.prepareStatement("UPDATE goods SET price = 2500 WHERE id = ? AND name = ?");
            statement4.setInt(1, id);
            statement4.setString(2, name);
            int res4 = statement4.executeUpdate();
            if ( res4 > 0) System.out.println("Успешно обновлено\n");

            statement6 = connection.createStatement();
            ResultSet resultSet6 = statement5.executeQuery("SELECT * FROM goods");
            System.out.println("<=====Список товаров после обновления=====>");
            while (resultSet6.next()){
                System.out.println(resultSet6.getInt(1)+ " " + resultSet6.getString(2) + " " +  resultSet6.getInt(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement1.close();
                statement2.close();
                statement3.close();
                statement4.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


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

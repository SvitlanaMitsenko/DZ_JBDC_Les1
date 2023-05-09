package Task3_5;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB2";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        registerDriver();
        Connection connection = null;
        Statement statement = null;
        Statement statement2 = null;
        PreparedStatement statement3 = null;
        PreparedStatement statement4 = null;

        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Employee> employees2 = new ArrayList<>();
        ArrayList<Employee> employees3 = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            statement.addBatch("CREATE TABLE Employees (id INT AUTO_INCREMENT NOT NULL, name NVARCHAR(30), phone CHAR(12) NOT NULL UNIQUE, PRIMARY KEY (id));");
            statement.addBatch("INSERT INTO Employees(name, phone) VALUES ('Петро',  '(093)1231212'), ('Василий', '(095)2313244'),('Иван',  '(097)4231212'), ('Николай',  '(098)5231212'), ('Назар',  '(099)6231212');");
            statement.addBatch("CREATE TABLE EmployeeWorkInfo (id INT primary key NOT NULL, position NVARCHAR(30), salary double(7, 2) NOT NULL, foreign key (id) references Employees(id));");
            statement.addBatch("INSERT INTO EmployeeWorkInfo (id, position, salary) VALUES (1, 'Менеджер',  25000), (2, 'Директор', 50000), (3, 'Слесарь', 7000), (4, 'Бухгалтер', 10000),(5, 'Водитель',  8000);");
            statement.addBatch("CREATE TABLE EmployeePersonalInfo\n" +
                    "(\t\n" +
                    "\t id INT primary key NOT NULL,\n" +
                    "     maritalStatus NVARCHAR(8),\n" +
                    "     birthday DATE,\n" +
                    "     address NVARCHAR(50) NOT NULL,\n" +
                    "     foreign key (id) references Employees(id)\n" +
                    ");");
            statement.addBatch("INSERT INTO EmployeePersonalInfo\n" +
                    "( id, maritalStatus, birthday, address)\n" +
                    "VALUES\n" +
                    "(1, 'не женат', '1975-03-16', 'Киев'),\n" +
                    "(2, 'женат',    '1970-01-15', 'Киев'),\n" +
                    "(3, 'женат',    '1990-04-16', 'Кривой Рог'),\n" +
                    "(4, 'женат',    '1981-08-01', 'Одесса'),\n" +
                    "(5, 'не женат', '1995-11-10', 'Одесса');");
            statement.executeBatch();

            statement2 = connection.createStatement();
            ResultSet resultSet = statement2.executeQuery("SELECT name, phone, address\n" +
                    "\tFROM employees AS a\n" +
                    "\tINNER JOIN EmployeePersonalInfo AS b\n" +
                    "    ON a.id = b.id;\n");

            String status  = "не женат";
            statement3 = connection.prepareStatement("SELECT name, phone, birthday, maritalStatus\n" +
                    "\tFROM employees AS a\n" +
                    "\tINNER JOIN EmployeePersonalInfo AS b\n" +
                    "    ON a.id = b.id\n" +
                    "    WHERE b.maritalStatus = ?;");
            statement3.setString(1, status);
            ResultSet resultSet2 = statement3.executeQuery();

            String position  = "менеджер";
            statement4 = connection.prepareStatement("SELECT name, phone, birthday, position\n" +
                    "\tFROM employees AS a\n" +
                    "\tLEFT JOIN EmployeePersonalInfo AS b\n" +
                    "    ON a.id = b.id\n" +
                    "\tLEFT JOIN EmployeeWorkInfo AS c\n" +
                    "    ON a.id = c.id\n" +
                    "    WHERE c.position = ?");
            statement4.setString(1, position);
            ResultSet resultSet3= statement4.executeQuery();


            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
            while (resultSet2.next()) {
                employees2.add(new Employee(resultSet2.getString(1), resultSet2.getString("phone"),
                                           resultSet2.getString(4), resultSet2.getDate(3)));
            }
            while (resultSet3.next()) {
                employees3.add(new Employee(resultSet3.getString(1), resultSet3.getString("phone"),resultSet3.getString("position"),
                                            0, resultSet3.getDate("birthday") ));
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
        System.out.println("<========Сотрудники(запрос 1)========>");
        for(Employee e: employees){
            System.out.println(e.toString_select1());
        }
        System.out.println("\n<========Сотрудники(запрос 2)========>");
        employees2.forEach((item)-> System.out.println(item.toString_select2()));
        System.out.println("\n<========Сотрудники(запрос 3)========>");
        employees3.forEach((item)-> System.out.println(item.toString_select3()));
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

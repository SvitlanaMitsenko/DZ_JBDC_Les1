package Task3_5;


import java.util.Date;

public class Employee {
public Employee(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Employee(String name, String phone, String maritalStatus, Date birthday) {
        this.name = name;
        this.phone = phone;
        this.maritalStatus = maritalStatus;
        this.birthday = birthday;
    }

    public Employee(String name, String phone, String position, double salary, Date birthday) {
        this.name = name;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.birthday = birthday;
    }

    int id;
    String name;
    String phone;
    String position;
    double salary;
    String  maritalStatus;
    Date birthday;
    String address;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }

    public String toString_select1() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
    public String toString_select2() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", birthday=" + birthday +
                '}';
    }
    public String toString_select3() {
        return "Employee{" +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", position='" + position + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

package entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Employee.deleteAllRows", query = "DELETE from Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private String name;

    @Column
    private String address;

    @Column
    private double salary;


    public Employee(String name, String address)
    {
        this.name = name;
        this.address = address;
    }

    public Employee(){}

    public Employee(String name, String address, double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return
                 name + '\n' +
                 address + '\n' +
                 + salary;
    }
}

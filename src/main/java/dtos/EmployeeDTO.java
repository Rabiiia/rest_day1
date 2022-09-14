package dtos;

import entities.Employee;
import entities.RenameMe;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {

    private int id;
    private String name;
    private String address;


    //kan ikke finde ud af at populate ordentligt
    public EmployeeDTO(Employee employee) {
            if(employee.getId() != null)
            {
                this.id = employee.getId();
            }
            this.name = employee.getName();
            this.address = employee.getAddress();
    }

    //To get list and use it in facade(getAll) and then use it in GET method in Rest API
    public static List<EmployeeDTO> getDtos(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList();
        employees.forEach(employee->employeeDTOS.add(new EmployeeDTO(employee)));
        return employeeDTOS;
    }

    public int getId() {
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


}

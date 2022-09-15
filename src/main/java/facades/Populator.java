/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade employeeFacade = EmployeeFacade.getEmployeeFacade(emf);
        employeeFacade.createEmployee(new EmployeeDTO(new Employee("Johannes", "Falkoner alle 5")));
        employeeFacade.createEmployee(new EmployeeDTO(new Employee("Marianne", "Vænget 7")));
        employeeFacade.createEmployee(new EmployeeDTO(new Employee("Pia", "Søborg Hovedgade 43")));
//        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
//        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));

    }
    
    public static void main(String[] args) {
        populate();
    }
}

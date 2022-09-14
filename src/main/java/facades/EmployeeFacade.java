package facades;

import dtos.EmployeeDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.eclipse.persistence.jpa.JpaHelper.getEntityManager;

public class EmployeeFacade {

    private static EmployeeFacade instance;

    private static EntityManagerFactory emf;

    private EmployeeFacade(){}

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee createEmployee(String name, String address, double salary) {

        EntityManager em = emf.createEntityManager();

        Employee employee = new Employee(name, address, salary);
        em.getTransaction().begin();
        em.persist(employee);
        //ryger ned i databasen, og den bliver autogenereret
        em.getTransaction().commit();
        em.close();
        return employee;

    }

    //skaber endnu en metode men det er af datatype EmployeeDTO samme metodenavn, derfor forstyrrer ik test
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO.getName(), employeeDTO.getAddress());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            System.out.println(employee);
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(employee);
    }



    public Employee getEmployeeById(int id) {

        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        return employee;
    }

    public List<Employee> getEmployeesByName(String name) {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList();
        }
        finally{

            em.close();

        }
    }

    public List<Employee> getEmployees() {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        }
        finally{

            em.close();

        }
    }

    //vil gerne skrive getEmployees her men kan ikke ellers forstyrrer det min test?
    public List<EmployeeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        return EmployeeDTO.getDtos(employees);
    }

    public List<Employee> getEmployeesWithHighestSalary() {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary desc", Employee.class);
            return query.getResultList();
        }
        finally{

            em.close();

        }
    }
}

package facades;

import entities.Employee;
import entities.RenameMe;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    private static Employee e1, e2;

    public EmployeeTest() {
    }


    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.getEmployeeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }


    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        e1 = new Employee("Mark", "Fredensborg alle 2", 45000);
        e2 = new Employee("Anne", "Juni Allé 1", 67000.35);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(e1);
            em.persist(e2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    @Test
    public void testCreateEmployee() throws Exception {
        String expected = "Ali\nNørre alle 2\n30000.0";
        Employee actual = facade.createEmployee("Ali", "Nørre alle 2", 30000);
        assertEquals(expected, actual.toString());
        System.out.println(actual);
    }

    @Test
    public void testgetEmployeeById() throws Exception {
        int expected = e1.getId();
        int actual = facade.getEmployeeById(expected).getId();
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    public void testGetEmployeesByName() throws Exception {
        int actual = facade.getEmployeesByName("Mark").size();
        int expected = 1;
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    public void testGetEmployees() throws Exception {
        int actual = facade.getEmployees().size();
        int expected = 2;
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    public void testGetEmployeesWithHighestSalary() throws Exception {
        List<Employee> actual = facade.getEmployeesWithHighestSalary();
        double expected = 67000.35;

        assertEquals(expected, actual.get(0).getSalary());
        System.out.println(actual.get(0));
    }




}
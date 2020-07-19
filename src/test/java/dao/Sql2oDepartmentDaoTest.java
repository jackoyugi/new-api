package dao;

import models.Department;
import models.Employees;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oEmployeesDao employeesDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectingString= "jdbc:postgresql://localhost:5432/newsapi_test";
        Sql2o sql2o = new Sql2o(connectingString,"jackoyugi","00100");
        employeesDao = new Sql2oEmployeesDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        employeesDao.clearAll();
        departmentDao.clearAll();
    }
    @AfterClass
    public static void shutdown() throws Exception{
        conn.close();
    }
    @Test
    public void addDepo() {
        Department department = setupDepartment();
        departmentDao.add(department);
        int departId = department.getId();
        assertEquals(departId,department.getId());
    }
    @Test
    public void findById() {
        Department department = setupDepartment();
        departmentDao.add(department);
        Department department1 = departmentDao.findById(department.getId());
        assertEquals(department,department1);
    }

    @Test
    public void getAll() {
        Department department = setupDepartment();
        departmentDao.add(department);
        Department department1 = setupDepartment();
        departmentDao.add(department1);
        assertTrue(departmentDao.getAll().contains(department));
        assertTrue(departmentDao.getAll().contains(department1));
    }
    @Test
    public void  addDepartmentToEmployees() {
        Department department = setupDepartment();
        departmentDao.add(department);
        Employees employees = new Employees("jane", "nurse", "patient-care","123-Nairobi");
        employeesDao.add(employees);
        Employees employees1 = new Employees("jane", "nurse", "patient-care","123-Nairobi");
        employeesDao.add(employees1);
        departmentDao.addDepartmentToEmployees(department,employees);
        departmentDao.addDepartmentToEmployees(department,employees1);
        Employees[] addDeptToEmployee = {employees,employees1};
        assertEquals(Arrays.asList(addDeptToEmployee),departmentDao.getAllEmployeesForADepartment(department.getId()));
    }

    @Test
    public void clearAll() {
        Department department = setupDepartment();
        departmentDao.add(department);
        departmentDao.clearAll();
        assertEquals(0,departmentDao.getAll().size());
    }
    @Test
    public void getAllNews() {

    }



    public Department setupDepartment(){
        return new Department("clinics", "treatments", 2);
    }

}
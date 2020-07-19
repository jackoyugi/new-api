package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeesTest {
    @Test
    public void EmployeesInstantiatesCorrectly_true() throws Exception {
        Employees employees = setupEmployees();
        assertTrue(employees instanceof Employees);
    }
    @Test
    public void EmployeesInstantiatesCorrectlyWith_Values()throws Exception{
        Employees employees= setupEmployees();
        assertEquals("jane",employees.getName());
        assertEquals("nurse",employees.getPosition());
        assertEquals("patient-care",employees.getRole());
        assertEquals("123-Nairobi",employees.getAddress());
    }
    @Test
    public void setId()throws Exception{
        Employees employees = setupEmployees();
        employees.setId(3);
        assertNotEquals(2,employees.getId());

    }
    public Employees setupEmployees(){
        return new Employees("jane", "nurse", "patient-care","123-Nairobi");
    }



}
package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {


    @Test
    public void DepartmentInstantiatesCorrectly() throws Exception{
        Department department =setupDepartment();
        assertTrue(department instanceof Department);
    }

    @Test
    public void Department_instantiatesWithCorrectValues() throws Exception{
        Department department = setupDepartment();
        assertEquals("clinics", department.getDepartment());
        assertEquals("treatments",department.getDescription());
        assertEquals(2,department.getNumber_employees());
    }

    @Test
    public void setId() throws Exception{
        Department department = setupDepartment();
        department.setId(3);
        assertNotEquals(2,department.getId());
    }

    public Department setupDepartment(){
        return new Department("clinics", "treatments", 2);
    }
}
package models;

import java.util.Objects;

public class Department {
    private  String department;
    private String description;
    private int number_employees;
    private int id;

    public Department(String department, String description, int number_employees){
        this.department=department;
        this.description=description;
        this.number_employees=number_employees;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber_employees() {
        return number_employees;
    }

    public void setNumber_employees(int number_employees) {
        this.number_employees = number_employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getNumber_employees() == that.getNumber_employees() &&
                getId() == that.getId() &&
                getDepartment().equals(that.getDepartment()) &&
                getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment(), getDescription(), getNumber_employees(), getId());
    }
}

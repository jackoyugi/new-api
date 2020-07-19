package models;

import java.util.Objects;

public class Employees {
    private String name;
    private String position;
    private String role;
    private String address;
    private int id;

    public Employees(String name, String position, String role, String address){
        this.name=name;
        this.position=position;
        this.role=role;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!(o instanceof Employees)) return false;
        Employees employees = (Employees) o;
        return getId() == employees.getId() &&
                getName().equals(employees.getName()) &&
                getPosition().equals(employees.getPosition()) &&
                getRole().equals(employees.getRole()) &&
                getAddress().equals(employees.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPosition(), getRole(), getAddress(), getId());
    }
}

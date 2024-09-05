package com.example.demo.dto;



public class EmployeeDTO {

    private Long id;
    private String employeeNumber;
    private String initials;
    private String lastName;
    private String designation;
    private boolean activeStatus;

    // Default constructor
    public EmployeeDTO() {
    }

    // Constructor with all fields
    public EmployeeDTO(Long id, String employeeNumber, String initials, String lastName, String designation, boolean activeStatus) {
        this.id = id;
        this.employeeNumber = employeeNumber;
        this.initials = initials;
        this.lastName = lastName;
        this.designation = designation;
        this.activeStatus = activeStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }
}

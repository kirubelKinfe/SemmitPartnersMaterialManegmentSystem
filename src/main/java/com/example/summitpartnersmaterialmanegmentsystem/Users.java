package com.example.summitpartnersmaterialmanegmentsystem;

public class Users {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String emp_id;
    private String gender;
    private String jobTitle;
    private String position;
    private String department;
    private String privilege;

    public Users(int id,String username, String password,String fullName,
                 String emp_id,String gender,String jobTitle,
                 String position,String department,String privilege) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.emp_id = emp_id;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.position = position;
        this.department = department;
        this.privilege = privilege;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}

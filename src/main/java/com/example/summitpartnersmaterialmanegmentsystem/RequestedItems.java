package com.example.summitpartnersmaterialmanegmentsystem;

public class RequestedItems {
    private int id;
    private String full_name;
    private String department;
    private String requested;
    private String request_date;
    private String status;

    public RequestedItems(int id, String full_name, String department, String requested, String request_date, String status) {
        this.id = id;
        this.full_name = full_name;
        this.department = department;
        this.requested = requested;
        this.request_date = request_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public String getRequest_date() {
        return request_date;
    }

    public void setRequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

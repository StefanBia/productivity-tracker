package com.productivity.productivitytracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.List;


public class Company {

    private Integer companyId;

    private String companyName;


    private List<User> users;

    public Company( String companyName) {

        this.companyName = companyName;
    }

    public Company() {

    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

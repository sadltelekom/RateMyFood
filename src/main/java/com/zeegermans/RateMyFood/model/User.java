package com.zeegermans.RateMyFood.model;

public class User {
    private long id;
    private String name;
    private String realName;
    private String status;
    private String email;
    private String password;

    public User(String name, String realName, String status, String email, String password) {
        this.name = name;
        this.realName = realName;
        this.status = status;
        this.email = email;
        this.password = password;
    }

    public User(long id, String name, String realName, String status, String email, String password) {
        this.id = id;
        this.name = name;
        this.realName = realName;
        this.status = status;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

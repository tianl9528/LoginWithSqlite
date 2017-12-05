package com.tianl9528.loginwithsqlite.model;

/**
 * Created by tianl9528 on 2017/12/1.
 */

public class User {
    private int id;
    private String name;
    private String pwd;
    private String email;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;

    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }
}

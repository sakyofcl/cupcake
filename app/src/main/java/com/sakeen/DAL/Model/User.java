package com.sakeen.DAL.Model;

public class User {
    public int userId;
    public String name;
    public String password;
    public int phone;
    public int userType;
    public String address;
    public boolean isAdmin = userType == 1;
}

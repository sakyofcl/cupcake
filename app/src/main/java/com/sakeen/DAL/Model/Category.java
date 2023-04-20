package com.sakeen.DAL.Model;

public class Category {
    public Category() {
    }

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int categoryId;
    public String name;
}
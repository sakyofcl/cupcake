package com.sakeen.DAL;

import android.content.Context;
import com.sakeen.DAL.Repository.CakeRepository;
import com.sakeen.DAL.Repository.CategoryRepository;
import com.sakeen.DAL.Repository.OrderRepository;
import com.sakeen.DAL.Repository.UserRepository;

public class UnitOfWork {
    DBManager dbManager;
    public UserRepository user;
    public OrderRepository order;
    public CakeRepository cake;
    public CategoryRepository category;

    public UnitOfWork(Context context) {
        this.dbManager = new DBManager(context);
        this.user = new UserRepository(this.dbManager);
        this.order = new OrderRepository(this.dbManager);
        this.category = new CategoryRepository(this.dbManager);
        this.cake = new CakeRepository(this.dbManager, this.category);
    }
}

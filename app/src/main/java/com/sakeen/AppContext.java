package com.sakeen;

import android.content.SharedPreferences;

public class AppContext {
    SharedPreferences context;
    public static String name = "context";
    SharedPreferences.Editor editor;
    public AppContext(SharedPreferences context) {
        this.context = context;
        this.editor = this.context.edit();
    }

    public boolean isLogin(){
        return this.context.getBoolean("isLogin", false);
    }
    public boolean isAdmin(){
        return this.context.getBoolean("isAdmin", false);
    }
    public int getUserId(){
        return this.context.getInt("userId", 0);
    }
    public String getUserName(){
        return this.context.getString("name", "");
    }

    public void setIsLogin(boolean v){
        this.editor.putBoolean("isLogin", v);
    }
    public void setIsAdmin(boolean v){
        this.editor.putBoolean("isAdmin", v);
    }
    public void setUserId(int v){
        this.editor.putInt("userId", v);
    }
    public void setUserName(String v){
        this.editor.putString("name", v);
    }

    public void save(){
        this.editor.commit();
    }


}

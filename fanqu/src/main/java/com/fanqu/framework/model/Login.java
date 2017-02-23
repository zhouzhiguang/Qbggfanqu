package com.fanqu.framework.model;

/**
 * 登录帮助类
 */

public class Login {
    public  String username;
    public  String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

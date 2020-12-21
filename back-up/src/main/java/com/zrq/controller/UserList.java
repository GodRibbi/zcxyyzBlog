package com.zrq.controller;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<User> users =new ArrayList<>();

    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

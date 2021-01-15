package com.zrq.controller;

public class User {
    private String useremail;
    private String password;
    public User(String useremail, String password) {
        this.useremail = useremail;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }


    @Override
    public String toString() {
        return "User [password=" + password + ", useremail=" + useremail 
                + "]";
    }
}

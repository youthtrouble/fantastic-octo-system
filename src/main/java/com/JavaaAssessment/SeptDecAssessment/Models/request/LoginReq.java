package com.JavaaAssessment.SeptDecAssessment.Models.request;

public class LoginReq {
    private String username;
    private String password;

    public LoginReq(String usermname, String password) {
        this.username = usermname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usermname) {
        this.username = usermname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

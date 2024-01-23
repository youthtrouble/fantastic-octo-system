package com.JavaaAssessment.SeptDecAssessment.Models.request;

/**
 * Request class for login
 * This class is used to get the username and password from the user
 * The username and password are used to authenticate the user
 * The username and password are passed in the request body as a JSON object
 * The username and password are used to authenticate the user
 */
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

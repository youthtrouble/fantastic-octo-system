package com.JavaaAssessment.SeptDecAssessment.Models.request;

public class RegistrationRequest {
    private String username;
    private String password;


    public RegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegistrationRequest() {

    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String toString() {
    	return "Username: " + username + " Password: " + password;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}

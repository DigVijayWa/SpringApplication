package com.SpringAppVersion2.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    private String userName;

    @NotBlank
    private String password;


}

package com.demo.subbucocociti_minitask.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subbu on 23/6/17.
 */

public class User {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.demo.subbucocociti_minitask.request_data;

import com.demo.subbucocociti_minitask.model.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by subbu on 23/6/17.
 */

public class RequestLogin {
    @SerializedName("user")
    private User user;

    public RequestLogin(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.demo.subbucocociti_minitask.request_data;

import com.demo.subbucocociti_minitask.model.User;

/**
 * Created by subbu on 23/6/17.
 */

public class RequestLogin {

    private User user;

    public RequestLogin(User user){
        user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

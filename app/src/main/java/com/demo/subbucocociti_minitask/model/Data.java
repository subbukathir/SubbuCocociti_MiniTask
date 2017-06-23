package com.demo.subbucocociti_minitask.model;

import com.demo.subbucocociti_minitask.response_data.Response_Login;
import com.google.gson.annotations.SerializedName;

/**
 * Created by subbu on 23/6/17.
 */

public class Data {

    @SerializedName("user")
    private Response_Login.User user;

    public Response_Login.User getUser() {
        return user;
    }

    public void setUser(Response_Login.User user) {
        this.user = user;
    }

}

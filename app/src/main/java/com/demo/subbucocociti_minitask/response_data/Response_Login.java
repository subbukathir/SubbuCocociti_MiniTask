package com.demo.subbucocociti_minitask.response_data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by subbu on 23/6/17.
 */

public class Response_Login {
    @SerializedName("status")
    private String status;
    @SerializedName(" info")
    private String info;
    @SerializedName("user")
    private User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public class User {

        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("email")
        private String email;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
}

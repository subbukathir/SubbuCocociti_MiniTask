package com.demo.subbucocociti_minitask.response_data;

import com.demo.subbucocociti_minitask.model.ShowCase;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by subbu on 23/6/17.
 */

public class Response_ListData {
    @SerializedName("status")
    private String status;
    @SerializedName("info")
    private String info;
    @SerializedName("showcases")
    private List<ShowCase> showcases = null;

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

    public List<ShowCase> getShowcases() {
        return showcases;
    }

    public void setShowcases(List<ShowCase> showcases) {
        this.showcases = showcases;
    }
}

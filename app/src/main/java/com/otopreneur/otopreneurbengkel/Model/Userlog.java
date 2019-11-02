package com.otopreneur.otopreneurbengkel.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userlog {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Userlog(Integer id, String activity, String timestamp) {
        this.id = id;
        this.activity = activity;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

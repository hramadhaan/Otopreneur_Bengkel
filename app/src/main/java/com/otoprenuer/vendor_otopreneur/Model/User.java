package com.otoprenuer.vendor_otopreneur.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("userdata")
    @Expose
    private Userdata userdata;
    @SerializedName("userorder")
    @Expose
    private List<Userorder> userorder;
    @SerializedName("userlog")
    @Expose
    private List<Userlog> userlog;

    public User(Userdata userdata, List<Userorder> userorder, List<Userlog> userlog) {
        this.userdata = userdata;
        this.userorder = userorder;
        this.userlog = userlog;
    }

    public Userdata getUserdata() {
        return userdata;
    }

    public void setUserdata(Userdata userdata) {
        this.userdata = userdata;
    }

    public List<Userorder> getUserorder() {
        return userorder;
    }

    public void setUserorder(List<Userorder> userorder) {
        this.userorder = userorder;
    }

    public List<Userlog> getUserlog() {
        return userlog;
    }

    public void setUserlog(List<Userlog> userlog) {
        this.userlog = userlog;
    }
}

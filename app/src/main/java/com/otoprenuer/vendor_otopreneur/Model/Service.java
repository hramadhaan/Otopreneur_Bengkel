package com.otoprenuer.vendor_otopreneur.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vendor")
    @Expose
    private Integer vendor;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("venichle")
    @Expose
    private String venichle;
    @SerializedName("cost")
    @Expose
    private Integer cost;

    public Service(Integer id, Integer vendor, String service, String venichle, Integer cost) {
        this.id = id;
        this.vendor = vendor;
        this.service = service;
        this.venichle = venichle;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVendor() {
        return vendor;
    }

    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVenichle() {
        return venichle;
    }

    public void setVenichle(String venichle) {
        this.venichle = venichle;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}

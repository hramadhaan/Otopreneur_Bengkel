package com.otoprenuer.vendor_otopreneur.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("service_code")
    @Expose
    private String serviceCode;

    public Variant(Integer id, String serviceName, String serviceCode) {
        this.id = id;
        this.serviceName = serviceName;
        this.serviceCode = serviceCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}

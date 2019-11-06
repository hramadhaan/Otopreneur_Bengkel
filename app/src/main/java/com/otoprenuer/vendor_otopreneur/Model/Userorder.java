package com.otoprenuer.vendor_otopreneur.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userorder {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("invoice_no")
    @Expose
    private Integer invoiceNo;
    @SerializedName("veniche_type")
    @Expose
    private String venicheType;
    @SerializedName("customer")
    @Expose
    private Integer customer;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("vendor")
    @Expose
    private Integer vendor;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("duration")
    @Expose
    private Object duration;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("venichle_series")
    @Expose
    private String venichleSeries;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customerdata")
    @Expose
    private Customerdata customerdata;
}

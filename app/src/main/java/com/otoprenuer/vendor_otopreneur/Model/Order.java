package com.otoprenuer.vendor_otopreneur.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {
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
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("venichle_series")
    @Expose
    private String venichleSeries;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customerdata")
    @Expose
    private Customerdata customerdata;
    @SerializedName("vendordata")
    @Expose
    private Vendordata vendordata;

    public Order(Integer id, Integer invoiceNo, String venicheType, Integer customer, String location, String note, Integer vendor, Object price, Object duration, String time, String service, String latitude, String longitude, String venichleSeries, String status, Customerdata customerdata, Vendordata vendordata) {
        this.id = id;
        this.invoiceNo = invoiceNo;
        this.venicheType = venicheType;
        this.customer = customer;
        this.location = location;
        this.note = note;
        this.vendor = vendor;
        this.price = price;
        this.duration = duration;
        this.time = time;
        this.service = service;
        this.latitude = latitude;
        this.longitude = longitude;
        this.venichleSeries = venichleSeries;
        this.status = status;
        this.customerdata = customerdata;
        this.vendordata = vendordata;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getVenicheType() {
        return venicheType;
    }

    public void setVenicheType(String venicheType) {
        this.venicheType = venicheType;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getVendor() {
        return vendor;
    }

    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getVenichleSeries() {
        return venichleSeries;
    }

    public void setVenichleSeries(String venichleSeries) {
        this.venichleSeries = venichleSeries;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customerdata getCustomerdata() {
        return customerdata;
    }

    public void setCustomerdata(Customerdata customerdata) {
        this.customerdata = customerdata;
    }

    public Vendordata getVendordata() {
        return vendordata;
    }

    public void setVendordata(Vendordata vendordata) {
        this.vendordata = vendordata;
    }
}

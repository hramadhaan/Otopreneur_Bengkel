package com.otopreneur.otopreneurbengkel.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeStatus {
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
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public ChangeStatus(Integer id, Integer invoiceNo, String venicheType, Integer customer, String location, String note, Integer vendor, Object price, Object duration, String time, String service, String venichleSeries, String status, String updatedAt) {
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
        this.venichleSeries = venichleSeries;
        this.status = status;
        this.updatedAt = updatedAt;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

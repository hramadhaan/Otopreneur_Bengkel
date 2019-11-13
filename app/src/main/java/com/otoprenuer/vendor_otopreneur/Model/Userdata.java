package com.otoprenuer.vendor_otopreneur.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Userdata {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("remember_token")
    @Expose
    private String rememberToken;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("otp_code")
    @Expose
    private Object otpCode;
    @SerializedName("settings")
    @Expose
    private String settings;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Userdata(Integer id, Integer roleId, String name, String email, Object phone, String avatar, Object emailVerifiedAt, String rememberToken, String status, Object otpCode, String settings, String createdAt, String updatedAt) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.emailVerifiedAt = emailVerifiedAt;
        this.rememberToken = rememberToken;
        this.status = status;
        this.otpCode = otpCode;
        this.settings = settings;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(Object otpCode) {
        this.otpCode = otpCode;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

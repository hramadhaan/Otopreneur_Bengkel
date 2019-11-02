package com.otopreneur.otopreneurbengkel.Data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.otopreneur.otopreneurbengkel.Model.Userdata;

public class AppState {
    private static AppState instance;
    private static final String TOKEN_KEY = "token_key";
    private static final String IS_LOGGED_IN = "is_logged_in";
    private static final String CURRENT_USER = "current_user";
    private static final String IS_ORDERED = "is_ordered";
    private static final String INVOICE_NO = "invoice_no";
    Context context;
    private AppState(){}
    private SharedPreferences pref;

    public static AppState getInstance() {
        if (instance == null) {
            synchronized (AppState.class) {
                if (instance == null) {
                    instance = new AppState();
                }
            }
        }
        return instance;
    }

    public void initSharedPrefs(Application application) {
        pref = application.getSharedPreferences("com.otopreneur.otopreneurbengkel.SHARED_PREF", Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        pref.edit().putString(TOKEN_KEY, token).apply();
    }

    public boolean hasToken() {
        return pref.contains(TOKEN_KEY);
    }

    public String provideToken() {
        return pref.getString(TOKEN_KEY, null);
    }

    public void removeToken() {
        pref.edit().remove(TOKEN_KEY).apply();
    }

    public void setInvoice(int invoice){
        pref.edit().putInt(INVOICE_NO,invoice).apply();
    }

    public int provideInvoice(){
        return pref.getInt(INVOICE_NO,0);
    }

    public void removeInvoice(){
        pref.edit().remove(INVOICE_NO).apply();
    }

    public  boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(Boolean status) {
        pref.edit().putBoolean(IS_LOGGED_IN, status).apply();
    }

    public boolean isOrdered(){
        return pref.getBoolean(IS_ORDERED,false);
    }

    public void logout() {
        removeToken();
        setIsLoggedIn(false);
    }

    public void setIsOrdered(Boolean ordered){
        pref.edit().putBoolean(IS_ORDERED,ordered).apply();
    }

    public void hapusOrder(){
        removeInvoice();
        setIsOrdered(false);
    }

    public void saveUser(Userdata userdata) {
        Gson gson = new Gson();
        pref.edit().putString(CURRENT_USER,gson.toJson(userdata)).apply();
    }

    public Userdata getUser() {
        Gson gson = new Gson();
        String userJson = pref.getString(CURRENT_USER, null);

        if (userJson == null) {
            return null;
        } else {
            return gson.fromJson(userJson, Userdata.class);
        }
    }
}

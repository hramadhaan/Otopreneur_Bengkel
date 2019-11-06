package com.otoprenuer.vendor_otopreneur;

import android.app.Application;

import com.otoprenuer.vendor_otopreneur.Data.AppState;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppState.getInstance().initSharedPrefs(this);
    }
}

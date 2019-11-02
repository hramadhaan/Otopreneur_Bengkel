package com.otopreneur.otopreneurbengkel;

import android.app.Application;

import com.otopreneur.otopreneurbengkel.Data.AppState;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppState.getInstance().initSharedPrefs(this);
    }
}

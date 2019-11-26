package com.otoprenuer.vendor_otopreneur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

public class LoginActivity extends AppCompatActivity {
    Button button_login;
    TextView forget;

    private AppState appStatee;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appStatee = AppState.getInstance();
        apiService = ApiUtils.getApiService();
        forget = findViewById(R.id.login_lupapassword);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,LupaPassword.class));
            }
        });

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        if (AppState.getInstance().isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }

        button_login = findViewById(R.id.login_button);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,IsiInput.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

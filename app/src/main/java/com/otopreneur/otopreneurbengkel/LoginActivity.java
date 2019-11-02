package com.otopreneur.otopreneurbengkel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.otopreneur.otopreneurbengkel.Data.AppState;
import com.otopreneur.otopreneurbengkel.Network.ApiService;
import com.otopreneur.otopreneurbengkel.Utils.ApiUtils;

public class LoginActivity extends AppCompatActivity {
    Button button_login;

    private AppState appStatee;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appStatee = AppState.getInstance();
        apiService = ApiUtils.getApiService();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(LoginActivity.this,"Good Bye",Toast.LENGTH_LONG).show();
    }
}

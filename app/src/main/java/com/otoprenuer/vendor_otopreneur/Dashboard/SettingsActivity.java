package com.otoprenuer.vendor_otopreneur.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.otoprenuer.vendor_otopreneur.DashboardActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.LoginActivity;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Settings.DataPersonal;
import com.otoprenuer.vendor_otopreneur.Settings.UbahPassword;

public class SettingsActivity extends AppCompatActivity {

    ImageView avatar;
    TextView name,logout,data,password;

    String hasilName;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (!AppState.getInstance().isLoggedIn()){
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        password = findViewById(R.id.settings_ganti);
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, UbahPassword.class));
            }
        });
        data = findViewById(R.id.settings_data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, DataPersonal.class));
            }
        });

        toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        avatar = findViewById(R.id.settings_image);

//        String urlAvatar = "data:image/jpeg;base64,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";

        name = findViewById(R.id.settings_nama);

        Userdata currentUser = AppState.getInstance().getUser();
        hasilName = currentUser.getName();

        Glide.with(this)
                .load(currentUser.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(avatar);
        name.setText(hasilName);

        logout = findViewById(R.id.settings_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppState.getInstance().isLoggedIn()){
                    AppState.getInstance().logout();
                    Intent intent = new Intent(SettingsActivity.this,LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SettingsActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}

package com.otoprenuer.vendor_otopreneur.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.Dashboard.SettingsActivity;
import com.otoprenuer.vendor_otopreneur.DashboardActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.LoginActivity;
import com.otoprenuer.vendor_otopreneur.Model.Status;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPassword extends AppCompatActivity {

    EditText lama,baru,confirm;
    Button button_baru,button_lama;
    Toolbar toolbar;

    String hasil_lama, hasil_baru, hasil_confirm, bandingin;

    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        bandingin = AppState.getInstance().getUser().getPassword();
        toolbar = findViewById(R.id.ubahpassword_toolbar);

        toolbar = findViewById(R.id.ubahpassword_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        baru = findViewById(R.id.ubahpassword_baru);
        confirm = findViewById(R.id.ubahpassword_confirm);
        button_baru = findViewById(R.id.ubahpassword_button_baru);

        button_baru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baru.getText().toString().equals(confirm.getText().toString())){
                    refresh();
                    finish();
                } else {
                    Toast.makeText(UbahPassword.this,"Password tidak sesuai",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    private void klik() {
//        if (lama.equals(bandingin)){
//            baru.setVisibility(View.VISIBLE);
//            baru.setEnabled(true);
//            confirm.setVisibility(View.VISIBLE);
//            confirm.setEnabled(true);
//            button_baru.setVisibility(View.VISIBLE);
//            button_baru.setEnabled(true);
//
//            lama.setVisibility(View.INVISIBLE);
//            button_lama.setVisibility(View.INVISIBLE);
//        } else {
//            Toast.makeText(UbahPassword.this,"Password Salah",Toast.LENGTH_LONG).show();
//        }
//    }

    private void refresh() {
        String email = AppState.getInstance().getUser().getEmail();
        Call<Status> statusCall = apiService.changePassword(email,confirm.getText().toString());
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        Toast.makeText(UbahPassword.this,"Berhasil Mengganti Password",Toast.LENGTH_LONG).show();
                        if (AppState.getInstance().isLoggedIn()){
                            AppState.getInstance().logout();
                            Intent intent = new Intent(UbahPassword.this,LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(UbahPassword.this,"Gagal Mengganti Password",Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(UbahPassword.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(UbahPassword.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UbahPassword.this, SettingsActivity.class);
        startActivity(intent);
        finish();
    }
}

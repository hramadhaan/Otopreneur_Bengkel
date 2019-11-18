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
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.LoginActivity;
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

        lama = findViewById(R.id.ubahpassword_lama);
        baru = findViewById(R.id.ubahpassword_baru);
        confirm = findViewById(R.id.ubahpassword_confirm);
        button_lama = findViewById(R.id.ubahpassword_button_lama);
        button_baru = findViewById(R.id.ubahpassword_button_baru);

        button_lama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
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
        int id_vendor = AppState.getInstance().getUser().getId();
        Call<Userdata> userdataCall = apiService.getStatus(id_vendor);
        userdataCall.enqueue(new Callback<Userdata>() {
            @Override
            public void onResponse(Call<Userdata> call, Response<Userdata> response) {
                if (response.isSuccessful()){
                    bandingin = response.body().getPassword();
                    if (lama.equals(bandingin)){
                        baru.setVisibility(View.VISIBLE);
                        baru.setEnabled(true);
                        confirm.setVisibility(View.VISIBLE);
                        confirm.setEnabled(true);
                        button_baru.setVisibility(View.VISIBLE);
                        button_baru.setEnabled(true);

                        lama.setVisibility(View.INVISIBLE);
                        button_lama.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(UbahPassword.this,"Password Salah",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(UbahPassword.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Userdata> call, Throwable t) {
                Toast.makeText(UbahPassword.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

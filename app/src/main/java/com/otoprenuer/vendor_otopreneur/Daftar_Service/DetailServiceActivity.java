package com.otoprenuer.vendor_otopreneur.Daftar_Service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.Dashboard.DaftarServiceActivity;
import com.otoprenuer.vendor_otopreneur.DashboardActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.EditService;
import com.otoprenuer.vendor_otopreneur.Model.Status;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailServiceActivity extends AppCompatActivity {

    EditText service,harga,kendaraan;
    Button save;
    String hasil_service,hasil_harga,hasil_kendaraan;

    TextView delete,judul;
    Toolbar toolbar;


    int id_service;

    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_service);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        toolbar = findViewById(R.id.detailservice_toolbar);
        judul = toolbar.findViewById(R.id.detailservice_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        service = findViewById(R.id.detailservice_tipeservice);
        harga = findViewById(R.id.detailservice_harga);
        save = findViewById(R.id.detailservice_button);
        kendaraan = findViewById(R.id.detailservice_tipekendaraan);
        delete = findViewById(R.id.detailservice_delete);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }



        Intent getIntet = getIntent();
        id_service = getIntet.getIntExtra("id_service",0);
        hasil_service = getIntet.getStringExtra("service");
        hasil_harga = getIntet.getStringExtra("harga");
        hasil_kendaraan = getIntet.getStringExtra("kendaraan");

        Toast.makeText(DetailServiceActivity.this,String.valueOf(id_service),Toast.LENGTH_LONG).show();

        service.setText(hasil_service);
        service.setEnabled(false);
        harga.setText(hasil_harga);
        kendaraan.setText(hasil_kendaraan);
        kendaraan.setEnabled(false);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    private void delete() {
        Call<Status> statusCall = apiService.deleteService(id_service);
        statusCall.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        Toast.makeText(DetailServiceActivity.this,"Berhasil Menghapus Service",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(DetailServiceActivity.this, DaftarServiceActivity.class));
                        finish();
                    } else {
                        Toast.makeText(DetailServiceActivity.this,"Gagal Menghapus Service",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(DetailServiceActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(DetailServiceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void update() {
        int hargaa = Integer.valueOf(harga.getText().toString());
        Call<EditService> editServiceCall = apiService.editService(id_service,hargaa);
        editServiceCall.enqueue(new Callback<EditService>() {
            @Override
            public void onResponse(Call<EditService> call, Response<EditService> response) {
                if (response.isSuccessful()){
                    Toast.makeText(DetailServiceActivity.this,"Berhasil Update Service",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DetailServiceActivity.this, DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(DetailServiceActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EditService> call, Throwable t) {
                Toast.makeText(DetailServiceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

}

package com.otoprenuer.vendor_otopreneur.Daftar_Service;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.DashboardActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Status;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Model.Variant;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddServiceActivity extends AppCompatActivity {

    EditText harga;
    Button save;
    Spinner service,jenisKendaraan;

    Toolbar toolbar;
    TextView judul;

    String hasil_tipeService, hasil_jenisKendaraan, hasil_status;

    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        toolbar = findViewById(R.id.addservice_toolbar);
        judul = toolbar.findViewById(R.id.addservice_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        jenisKendaraan = findViewById(R.id.addservice_jeniskendaraan);

        String[] kendaraan = {
                "Mobil",
                "Motor"
        };

        final ArrayAdapter<String> adapterKendaraan = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,kendaraan);
        adapterKendaraan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisKendaraan.setAdapter(adapterKendaraan);
        jenisKendaraan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hasil_jenisKendaraan = adapterKendaraan.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        service = findViewById(R.id.addservice_spinner);
        harga = findViewById(R.id.addservice_harga);
        save = findViewById(R.id.addservice_save);

        refresh();
        service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hasil_tipeService = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirim();
            }
        });


    }

    private void kirim() {
        Userdata currentUser = AppState.getInstance().getUser();
        int id = currentUser.getId();
        int haharga = Integer.valueOf(harga.getText().toString());

        Call<Status> call = apiService.createService(id,hasil_tipeService,hasil_jenisKendaraan,haharga);
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("success")){
                        save.setEnabled(false);
                        hasil_status = response.body().getStatus();
                        Toast.makeText(AddServiceActivity.this,hasil_status,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddServiceActivity.this, DashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(AddServiceActivity.this,"Gagal Membuat Service",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AddServiceActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                Toast.makeText(AddServiceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refresh() {
        Call<List<Variant>> call = apiService.getVariant();
        call.enqueue(new Callback<List<Variant>>() {
            @Override
            public void onResponse(Call<List<Variant>> call, Response<List<Variant>> response) {
                if (response.isSuccessful()){
                    List<Variant> variantList = response.body();
                    List<String> listSpinner = new ArrayList<String>();
                    List<String> listServiceCode = new ArrayList<String>();
                    for (int i=0;i<variantList.size();i++){
                        listSpinner.add(variantList.get(i).getServiceName());
                        listServiceCode.add(variantList.get(i).getServiceCode());
                    }
                    ArrayAdapter<String> adapterservice = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listServiceCode);
                    adapterservice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    service.setAdapter(adapterservice);
                } else {
                    Toast.makeText(AddServiceActivity.this,"Gagal memuat konten : "+response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Variant>> call, Throwable t) {

            }
        });
    }
}

package com.otoprenuer.vendor_otopreneur.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.Daftar_Service.AddServiceActivity;
import com.otoprenuer.vendor_otopreneur.Daftar_Service.DaftarServiceAdapter;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Service;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    TextView add,judul;
    int id_vendor;
    Toolbar toolbar;

    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_service);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        toolbar = findViewById(R.id.daftarservice_toolbar);
        judul = toolbar.findViewById(R.id.daftarservice_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        recyclerView = findViewById(R.id.daftarservice_recyccler);
        add = findViewById(R.id.daftarservice_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DaftarServiceActivity.this, AddServiceActivity.class));
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        refresh();

    }

    private void refresh() {
        Userdata currentuser = AppState.getInstance().getUser();
        id_vendor = currentuser.getId();

        Call<List<Service>> call = apiService.getService(id_vendor);
        call.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                if (response.isSuccessful()){
                    List<Service> services = response.body();
                    adapter = new DaftarServiceAdapter(getApplicationContext(),services);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    if (adapter.getItemCount()==0){
                        Toast.makeText(DaftarServiceActivity.this,"Daftar Service Tidak Ada",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(DaftarServiceActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Toast.makeText(DaftarServiceActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

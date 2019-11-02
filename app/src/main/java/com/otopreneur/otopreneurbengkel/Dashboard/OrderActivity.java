package com.otopreneur.otopreneurbengkel.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.otopreneur.otopreneurbengkel.Adapter.DaftarOrderAdapter;
import com.otopreneur.otopreneurbengkel.Data.AppState;
import com.otopreneur.otopreneurbengkel.Model.Order;
import com.otopreneur.otopreneurbengkel.Model.Userdata;
import com.otopreneur.otopreneurbengkel.Network.ApiService;
import com.otopreneur.otopreneurbengkel.R;
import com.otopreneur.otopreneurbengkel.Utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;
    TextView judul;

    String customer,tipeKendaraan,lokasi,tipeService;

    private AppState appState;
    private ApiService apiService;

    int id_vendor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        recyclerView = findViewById(R.id.order_recycler);
        toolbar = findViewById(R.id.order_toolbar);
        judul = toolbar.findViewById(R.id.order_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

//        INTENT
        Intent getIntent = getIntent();
        customer = getIntent.getStringExtra("customer");
        tipeKendaraan = getIntent.getStringExtra("tipekendaraan");
        lokasi = getIntent.getStringExtra("lokasi");
        tipeService = getIntent.getStringExtra("tipeservice");

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        refresh();

    }

    private void refresh() {
        Userdata currentUser = AppState.getInstance().getUser();
        id_vendor = currentUser.getId();
        Call<List<Order>> listCall = apiService.getOrderan(id_vendor);
        listCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()){
                    List<Order> orderList = response.body();
                    adapter = new DaftarOrderAdapter(getApplicationContext(),orderList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    if (adapter.getItemCount()==0){
                        Toast.makeText(OrderActivity.this,"Tidak Ada Order",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {

            }
        });
    }

}

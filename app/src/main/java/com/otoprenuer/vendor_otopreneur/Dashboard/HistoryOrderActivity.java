package com.otoprenuer.vendor_otopreneur.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.History_Order.HistoryAdapter;
import com.otoprenuer.vendor_otopreneur.Model.History;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;

    Toolbar toolbar;
    TextView judul;

    int invoice, id_vendor;
    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        recyclerView = findViewById(R.id.history_recycler);
        toolbar = findViewById(R.id.history_toolbar);
        judul = toolbar.findViewById(R.id.history_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        refresh();

    }

    private void refresh() {
        Userdata userdata = AppState.getInstance().getUser();
        id_vendor = userdata.getId();
        Call<List<History>> listCall = apiService.getHistory(id_vendor);
        listCall.enqueue(new Callback<List<History>>() {
            @Override
            public void onResponse(Call<List<History>> call, Response<List<History>> response) {
                if (response.isSuccessful()){
                    List<History> historyList = response.body();
                    adapter = new HistoryAdapter(getApplicationContext(),historyList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    if (adapter.getItemCount()==0){
                        Toast.makeText(HistoryOrderActivity.this,"Tidak ada History",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(HistoryOrderActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<History>> call, Throwable t) {
                Toast.makeText(HistoryOrderActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

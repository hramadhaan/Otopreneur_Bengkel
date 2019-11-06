package com.otoprenuer.vendor_otopreneur.History_Order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Invoice;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView judul,customer,tipeKendaraan,tipeService,lokasi;
    private AppState appState;
    private ApiService apiService;
    int invoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        toolbar=  findViewById(R.id.detailHistory_toolbar);
        judul = toolbar.findViewById(R.id.detailHistory_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        customer = findViewById(R.id.detailHistory_customer);
        tipeKendaraan = findViewById(R.id.detailHistory_tipekendaraan);
        tipeService = findViewById(R.id.detailHistory_tipeservice);
        lokasi = findViewById(R.id.detailHistory_lokasi);

        Intent getIntent = getIntent();
        invoice = getIntent.getIntExtra("invoice",0);

        refresh();

    }

    private void refresh() {
        Call<Invoice> invoiceCall = apiService.getOrder(invoice);
        invoiceCall.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                if (response.isSuccessful()){
                    customer.setText(response.body().getCustomerdata().getName());
                    tipeKendaraan.setText(response.body().getVenicheType());
                    tipeService.setText(response.body().getNote());
                    lokasi.setText(response.body().getLocation());

                    judul.setText("Invoice : "+String.valueOf(response.body().getInvoiceNo()));
                } else {

                }
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {

            }
        });
    }
}

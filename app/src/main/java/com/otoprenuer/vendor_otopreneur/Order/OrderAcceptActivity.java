package com.otoprenuer.vendor_otopreneur.Order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.DashboardActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.ChangeStatus;
import com.otoprenuer.vendor_otopreneur.Model.Invoice;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAcceptActivity extends AppCompatActivity {

    TextView finish, customer, tipeKendaraan, tipeService, lokasi;
    int invoice_no;
    String no_invoice, nomor, hasil_latitude, hasil_longtitude, hasil_lokasi;

    Button telepon;

    Toolbar toolbar;
    TextView judul;

    private AppState appState;
    private ApiService apiService;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        toolbar = findViewById(R.id.orderAccept_toolbar);
        judul = toolbar.findViewById(R.id.orderAccept_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judul.setText("Order : " + AppState.getInstance().provideInvoice());

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();
        refreshLayout = findViewById(R.id.orderAccept_swipe);

        customer = findViewById(R.id.orderAccept_customer);
        tipeKendaraan = findViewById(R.id.orderAccept_tipekendaraan);
        tipeService = findViewById(R.id.orderAccept_tipeservice);
        lokasi = findViewById(R.id.orderAccept_lokasi);
        telepon = findViewById(R.id.orderAccept_call);

        finish = findViewById(R.id.orderAccept_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishh();
            }
        });

        refresh();

        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + hasil_latitude + "," + hasil_longtitude + " (" + hasil_lokasi + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent panggil = new Intent(Intent.ACTION_DIAL);
                panggil.setData(Uri.fromParts("tel", nomor, null));
                startActivity(panggil);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe();
                refreshLayout.setRefreshing(false);
            }
        });

    }


    private void finishh() {
        int invoice = Integer.valueOf(AppState.getInstance().provideInvoice());
        Call<ChangeStatus> changeStatusCall = apiService.changeStatus(invoice, "finished");
        changeStatusCall.enqueue(new Callback<ChangeStatus>() {
            @Override
            public void onResponse(Call<ChangeStatus> call, Response<ChangeStatus> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(OrderAcceptActivity.this, "Status anda : " + response.body().getStatus(), Toast.LENGTH_LONG).show();
                    keluar();
                } else {
                    Toast.makeText(OrderAcceptActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ChangeStatus> call, Throwable t) {
                Toast.makeText(OrderAcceptActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void keluar() {
        if (AppState.getInstance().isOrdered()) {
            AppState.getInstance().hapusOrder();
            startActivity(new Intent(OrderAcceptActivity.this, DashboardActivity.class));
            finish();
        }
    }

    private void swipe() {
        invoice_no = AppState.getInstance().provideInvoice();
        Call<Invoice> getStatusCall = apiService.getOrder(invoice_no);
        getStatusCall.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                if (response.body().getStatus().equals("ordered")) {

                } else {
                    keluar();
                }
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                Toast.makeText(OrderAcceptActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refresh() {
        invoice_no = AppState.getInstance().provideInvoice();
        Call<Invoice> getOrder = apiService.getOrder(invoice_no);
        getOrder.enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                if (response.isSuccessful()) {
                    customer.setText(response.body().getCustomerdata().getName());
                    tipeKendaraan.setText(response.body().getVenicheType());
                    tipeService.setText(response.body().getNote());
                    lokasi.setText(response.body().getLocation());
                    lokasi.setPaintFlags(lokasi.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    nomor = response.body().getCustomerdata().getPhone();
                    hasil_latitude = response.body().getLatitude();
                    hasil_longtitude = response.body().getLongitude();
                    hasil_lokasi = response.body().getLocation();
                } else {
                    Toast.makeText(OrderAcceptActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                Toast.makeText(OrderAcceptActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

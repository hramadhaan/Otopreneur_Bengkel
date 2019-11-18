package com.otoprenuer.vendor_otopreneur.Order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class DetailOrderActivity extends AppCompatActivity {

    TextView customer,tipekendaraan,tipeservice,lokasi,judul;
    Button accept,cancel;

    Dialog dAccept,dCancel;
    String hasil_customer,hasil_tipekendaraan,hasil_tipeservice,hasil_lokasi, invoice_get,hasil_latitude,hasil_longtitude;

    Toolbar toolbar;

    String cek_waktu,cek_harga;

    int hasil_invoice,harga,time;
    int invoice;

    private AppState appState;
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        accept = findViewById(R.id.detailorder_accept);
        cancel = findViewById(R.id.detailorder_cancel);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        dAccept = new Dialog(this);


        toolbar = findViewById(R.id.detailorder_toolbar);
        judul = toolbar.findViewById(R.id.detailorder_judul);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customer = findViewById(R.id.detailorder_customer);
        tipekendaraan = findViewById(R.id.detailorder_tipekendaraan);
        tipeservice = findViewById(R.id.detailorder_tipeservice);
        lokasi = findViewById(R.id.detailorder_lokasi);

//        GET INTENT
        Intent intent = getIntent();
        hasil_customer = intent.getStringExtra("customer");
        hasil_lokasi = intent.getStringExtra("lokasi");
        hasil_tipekendaraan = intent.getStringExtra("tipekendaraan");
        hasil_tipeservice = intent.getStringExtra("tipeservice");
        hasil_invoice = intent.getIntExtra("invoice",0);
        hasil_latitude = intent.getStringExtra("latitude");
        hasil_longtitude = intent.getStringExtra("longtitude");

        customer.setText(hasil_customer);
        tipekendaraan.setText(hasil_tipekendaraan);
        tipeservice.setText(hasil_tipeservice);
        lokasi.setText(hasil_lokasi);
        lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + hasil_latitude + "," + hasil_longtitude + " (" + hasil_lokasi + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAccept();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ChangeStatus> changeStatusCall = apiService.changeStatus(hasil_invoice,"canceled");
                changeStatusCall.enqueue(new Callback<ChangeStatus>() {
                    @Override
                    public void onResponse(Call<ChangeStatus> call, Response<ChangeStatus> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(DetailOrderActivity.this,"Status anda : "+response.body().getStatus(),Toast.LENGTH_LONG).show();
                            keluar();
                        } else {
                            Toast.makeText(DetailOrderActivity.this,response.message(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangeStatus> call, Throwable t) {
                        Toast.makeText(DetailOrderActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void keluar() {
        startActivity(new Intent(DetailOrderActivity.this, DashboardActivity.class));
        finish();
    }

    private void dialogAccept() {

        TextView done;
        final EditText waktu,ongkos;
        dAccept.setContentView(R.layout.dialog_accept);
        waktu = dAccept.findViewById(R.id.accept_waktu);
        ongkos = dAccept.findViewById(R.id.accept_ongkos);
        done = dAccept.findViewById(R.id.accept_done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cek_harga = ongkos.getText().toString();
                cek_waktu = waktu.getText().toString();
                if (TextUtils.isEmpty(cek_harga)||TextUtils.isEmpty(cek_waktu)){
                    Toast.makeText(DetailOrderActivity.this,"Masukkan Estimasi Waktu dan Harga",Toast.LENGTH_LONG).show();
                } else {
                    harga = Integer.parseInt(ongkos.getText().toString());
                    time = Integer.parseInt(waktu.getText().toString());
                    Call<Invoice> processOrder = apiService.processAccept(hasil_invoice,harga,time);
                    processOrder.enqueue(new Callback<Invoice>() {
                        @Override
                        public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                            if (response.isSuccessful()){
                                invoice = response.body().getInvoiceNo();
                                appState.setInvoice(invoice);
                                appState.setIsOrdered(true);
                                startActivity(new Intent(DetailOrderActivity.this,OrderAcceptActivity.class));
                                finish();
                            } else {
                                Toast.makeText(DetailOrderActivity.this,response.message(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Invoice> call, Throwable t) {
                            Toast.makeText(DetailOrderActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                    dAccept.dismiss();
                }
            }
        });
        dAccept.show();
    }
}

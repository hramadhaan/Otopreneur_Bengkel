package com.otoprenuer.vendor_otopreneur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.Dashboard.DaftarServiceActivity;
import com.otoprenuer.vendor_otopreneur.Dashboard.HistoryOrderActivity;
import com.otoprenuer.vendor_otopreneur.Dashboard.OrderActivity;
import com.otoprenuer.vendor_otopreneur.Dashboard.SettingsActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Status;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.Order.OrderAcceptActivity;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    CardView daftarservice,order,history,settings;
    TextView nama;
    Switch status;
    RelativeLayout relativeLayout;

    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        if (AppState.getInstance().isOrdered()){
            startActivity(new Intent(DashboardActivity.this, OrderAcceptActivity.class));
            finish();
        }

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        if (!AppState.getInstance().isLoggedIn()){
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        nama = findViewById(R.id.dashboard_nama);
        status = findViewById(R.id.dashboard_aktif);
        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                   aktif();
                } else {
                    nonAktif();
                }
            }
        });

        Userdata currentUser = AppState.getInstance().getUser();
        nama.setText(currentUser.getName());
        daftarservice = findViewById(R.id.dashboard_cv_daftarservice);
        daftarservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, DaftarServiceActivity.class));
            }
        });
        order = findViewById(R.id.dashboard_cv_order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, OrderActivity.class));
            }
        });
        history = findViewById(R.id.dashboard_cv_history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, HistoryOrderActivity.class));
            }
        });
        settings = findViewById(R.id.dashboard_cv_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, SettingsActivity.class));
                finish();
            }
        });

    }

    private void nonAktif() {
        Userdata currentUser = AppState.getInstance().getUser();
            int id_vendor = currentUser.getId();
            Call<Status> ubahStatus = apiService.editStatus(id_vendor,"nonaktif");
            ubahStatus.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(DashboardActivity.this,"Status telah : "+response.body().getStatus(),Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DashboardActivity.this,response.message(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Toast.makeText(DashboardActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
    }

    private void aktif() {
        Userdata currentUser = AppState.getInstance().getUser();


            int id_vendor = currentUser.getId();
            Call<Status> ubahStatus = apiService.editStatus(id_vendor,"aktif");
            ubahStatus.enqueue(new Callback<Status>() {
                @Override
                public void onResponse(Call<Status> call, Response<Status> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(DashboardActivity.this,"Status telah : "+response.body().getStatus(),Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DashboardActivity.this,response.message(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Status> call, Throwable t) {
                    Toast.makeText(DashboardActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Userdata currentUser = AppState.getInstance().getUser();
        int id_vendor = currentUser.getId();
        Call<Userdata> getStatus = apiService.getStatus(id_vendor);
        getStatus.enqueue(new Callback<Userdata>() {
            @Override
            public void onResponse(Call<Userdata> call, Response<Userdata> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("aktif")){
                        status.setChecked(true);
                    } else {
                        status.setChecked(false);
                    }
                } else {
                    Toast.makeText(DashboardActivity.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Userdata> call, Throwable t) {
                Toast.makeText(DashboardActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}

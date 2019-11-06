package com.otoprenuer.vendor_otopreneur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.otoprenuer.vendor_otopreneur.Dashboard.DaftarServiceActivity;
import com.otoprenuer.vendor_otopreneur.Dashboard.HistoryOrderActivity;
import com.otoprenuer.vendor_otopreneur.Dashboard.OrderActivity;
import com.otoprenuer.vendor_otopreneur.Dashboard.SettingsActivity;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Order.OrderAcceptActivity;

public class DashboardActivity extends AppCompatActivity {
    CardView daftarservice,order,history,settings;
    TextView nama;
    RelativeLayout relativeLayout;
    AppState appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        nyalainStatus();

    }

    private void nyalainStatus() {

    }
}

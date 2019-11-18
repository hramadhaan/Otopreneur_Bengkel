package com.otoprenuer.vendor_otopreneur.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.R;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPersonal extends AppCompatActivity {

    TextView name,email,number;
    ImageView gambar;
    private AppState appState;
    private ApiService apiService;
    int id_vendor;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_personal);

        toolbar = findViewById(R.id.datapersonal_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        name = findViewById(R.id.datapersonal_name);
        email = findViewById(R.id.datapersonal_email);
        number = findViewById(R.id.datapersonal_telpon);

        gambar =findViewById(R.id.datapersonal_image);

        refresh();

    }

    private void refresh() {
        id_vendor = AppState.getInstance().getUser().getId();
        Call<Userdata> userdataCall = apiService.getStatus(id_vendor);
        userdataCall.enqueue(new Callback<Userdata>() {
            @Override
            public void onResponse(Call<Userdata> call, Response<Userdata> response) {
                if (response.isSuccessful()){
                    name.setText(response.body().getName());
                    email.setText(response.body().getEmail());
                    number.setText(response.body().getPhone());
                    Glide.with(getApplicationContext()).asBitmap().load(response.body().getAvatar()).into(gambar);
                } else {
                    Toast.makeText(DataPersonal.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Userdata> call, Throwable t) {
                Toast.makeText(DataPersonal.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

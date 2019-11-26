package com.otoprenuer.vendor_otopreneur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.otoprenuer.vendor_otopreneur.Data.AppState;
import com.otoprenuer.vendor_otopreneur.Model.Status;
import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaPassword extends AppCompatActivity {

    EditText email;
    Button submit;

    private AppState appState;
    private ApiService apiService;

    Toolbar toolbar;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();

        email = findViewById(R.id.lupapassword_email);
        submit = findViewById(R.id.lupapassword_button);
        progressBar = findViewById(R.id.lupapassword_progress);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                submit.setVisibility(View.INVISIBLE);
                Call<Status> statusCall = apiService.forgetPassword(email.getText().toString());
                statusCall.enqueue(new Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, Response<Status> response) {
                        if (response.isSuccessful()){
                            if (response.body().getStatus().equals("success")){
                                Toast.makeText(LupaPassword.this,"Cek Email : "+email.getText().toString()+" untuk mendapatkan password baru Anda",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                submit.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(LupaPassword.this,"Email Anda tidak terdaftar",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                submit.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(LupaPassword.this,response.message(),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            submit.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        Toast.makeText(LupaPassword.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        submit.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}

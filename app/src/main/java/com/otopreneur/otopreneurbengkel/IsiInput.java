package com.otopreneur.otopreneurbengkel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.otopreneur.otopreneurbengkel.Data.AppState;
import com.otopreneur.otopreneurbengkel.Model.Token;
import com.otopreneur.otopreneurbengkel.Model.User;
import com.otopreneur.otopreneurbengkel.Network.ApiService;
import com.otopreneur.otopreneurbengkel.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IsiInput extends AppCompatActivity {
    EditText email, password;
    Button login;
    ProgressBar progressBar;

    String hasilEmail, hasilPassword, hasilToken;
    private AppState appState;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_input);

        appState = AppState.getInstance();
        apiService = ApiUtils.getApiService();
        progressBar = findViewById(R.id.logininput_progress);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.color));
        }

//        if (AppState.getInstance().isLoggedIn()) {
//            startActivity(new Intent(IsiInput.this, DashboardActivity.class));
//            finish();
//        }

        email = findViewById(R.id.logininput_email);
        password = findViewById(R.id.logininput_password);
        login = findViewById(R.id.logininput_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                Login();
            }
        });

    }

    private void Login() {

        hasilEmail = email.getText().toString();
        hasilPassword = password.getText().toString();

        Call<Token> call = apiService.login(hasilEmail, hasilPassword);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    hasilToken = response.body().getToken();
                    if (hasilToken.equals("null")) {
                        login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(IsiInput.this, "Akun Anda tidak Terdaftar", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("TOKEN : ", hasilToken);
                        login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        login.setEnabled(false);
                        appState.setToken(hasilToken);
                        appState.setIsLoggedIn(true);
                        getuser();
                    }
                } else {
                    Toast.makeText(IsiInput.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Toast.makeText(IsiInput.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getuser() {
        apiService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    appState.saveUser(response.body().getUserdata());
                    Intent intent = new Intent(IsiInput.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IsiInput.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

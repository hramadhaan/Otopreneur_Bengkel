package com.otopreneur.otopreneurbengkel.Utils;

import com.otopreneur.otopreneurbengkel.Network.ApiService;
import com.otopreneur.otopreneurbengkel.Network.RetrofitClient;

public class ApiUtils {
    private ApiUtils(){}

    public static final String API_URL = "http://api-otopreneur.sobatteknologi.com/";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(API_URL).create(ApiService.class);
    }
}

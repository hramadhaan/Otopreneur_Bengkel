package com.otoprenuer.vendor_otopreneur.Utils;

import com.otoprenuer.vendor_otopreneur.Network.ApiService;
import com.otoprenuer.vendor_otopreneur.Network.RetrofitClient;

public class ApiUtils {
    private ApiUtils(){}

    public static final String API_URL = "http://api-otopreneur.sobatteknologi.com/";

    public static ApiService getApiService() {
        return RetrofitClient.getClient(API_URL).create(ApiService.class);
    }
}

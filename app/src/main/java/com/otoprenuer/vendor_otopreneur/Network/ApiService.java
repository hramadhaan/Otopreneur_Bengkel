package com.otoprenuer.vendor_otopreneur.Network;

import com.otoprenuer.vendor_otopreneur.Model.ChangeStatus;
import com.otoprenuer.vendor_otopreneur.Model.EditService;
import com.otoprenuer.vendor_otopreneur.Model.History;
import com.otoprenuer.vendor_otopreneur.Model.Invoice;
import com.otoprenuer.vendor_otopreneur.Model.Order;
import com.otoprenuer.vendor_otopreneur.Model.Service;
import com.otoprenuer.vendor_otopreneur.Model.SpesificOrder;
import com.otoprenuer.vendor_otopreneur.Model.Status;
import com.otoprenuer.vendor_otopreneur.Model.Token;
import com.otoprenuer.vendor_otopreneur.Model.User;
import com.otoprenuer.vendor_otopreneur.Model.Userdata;
import com.otoprenuer.vendor_otopreneur.Model.Variant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @FormUrlEncoded
    @POST("login/")
    Call<Token> login(@Field("email") String email, @Field("password") String password);

    @POST("getAPI/createUser/")
    Call<Token> createUser(@Body User User);

    @GET("roles")
    Call<User> getUser();

    @GET("getAPI/getServiceVariant")
    Call<List<Variant>> getVariant();

    @FormUrlEncoded
    @POST("getAPI/createService")
    Call<Status> createService(
            @Field("id_vendor") int id_vendor,
            @Field("service_code") String service_code,
            @Field("venichle") String venichle,
            @Field("cost") int cost
    );

    @GET("getAPI/getSpecificUserOrder/{id_vendor}")
    Call<List<SpesificOrder>> getSpesificOrder(
            @Path("id_vendor") int id_vendor
    );

    @GET("getAPI/processAcceptOrderVendor/{invoice}/{price}/{duration}")
    Call<Invoice> processAccept(
            @Path("invoice") int invoice,
            @Path("price") int price,
            @Path("duration") int duration
    );

    @GET("getAPI/getSpecificOrder/{invoice_no}")
    Call<Invoice> getOrder(
            @Path("invoice_no") int invoice_no
    );

    @GET("getAPI/getServicebyidvendor/{id_vendor}")
    Call<List<Service>> getService(
            @Path("id_vendor") int id_vendor
    );


    @GET("getAPI/getUsersVendorLogCF/{id_vendor}")
    Call<List<History>> getHistory(@Path("id_vendor") int id_vendor);

    @GET("getAPI/getUsersVendorLogWO/{id_vendor}")
    Call<List<Order>> getOrderan(
            @Path("id_vendor") int id_vendor
    );

    @GET("getAPI/changeStatus/{invoice_no}/{status}")
    Call<ChangeStatus> changeStatus(
            @Path("invoice_no") int invoice_no,
            @Path("status") String status
    );

    @GET("getAPI/deleteSpecificService/{id_service}")
    Call<Status> deleteService(
            @Path("id_service") int id_service
    );

    @FormUrlEncoded
    @POST("getAPI/editService")
    Call<EditService> editService(
            @Field("id_service") int id_service,
            @Field("cost") int cost
    );

    @GET("getAPI/editUserStatus/{id_vendor}/{status}")
    Call<Status> editStatus(
            @Path("id_vendor") int id_vendor,
            @Path("status") String status
    );

    @GET("getAPI/getSpecificVendor/{id_vendor}")
    Call<Userdata> getStatus(
      @Path("id_vendor") int id_vendor
    );

    @GET("getAPI/changePassword/{email}/{password}")
    Call<Status> changePassword(
            @Path("email") String email,
            @Path("password") String password
    );

    @GET("getAPI/forgetPassword/{email}")
    Call<Status> forgetPassword (
            @Path("email") String email
    );

}

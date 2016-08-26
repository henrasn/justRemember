package com.example.henrasetianugraha.e_commerce.API;

import com.example.henrasetianugraha.e_commerce.Model.Model;
import com.example.henrasetianugraha.e_commerce.Model.ModelDetail;
import com.google.gson.JsonElement;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Henra Setia Nugraha on 8/25/2016.
 */

public interface APIEndPoint {
    @GET(APIConfig.GET_PRODUCT)
    Call<JsonElement> getProducts();

    @GET(APIConfig.GET_PRODUCT+"/{idProduct}")
    Call<JsonElement> getDetailProducts(@Path("idProduct") String idProduct);
}

package com.example.henrasetianugraha.e_commerce.API;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Henra Setia Nugraha on 8/25/2016.
 */

public class ServiceManager {
    public static APIEndPoint getService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();

                Request.Builder builder=request.newBuilder()
                        .addHeader("Access-Control-Allow-Origin","*")
                        .addHeader("Access-Control-Allow-Origin-Headers","Accept, Content-Type, If-None-Match, X-If-None-Match")
                        .addHeader("Access-Control-Expose-Headers","Location, Warning, Etag")
                        .addHeader("Access-Control-Allow-Headers","Content-Type, If-None-Match, X-If-None-Match, Authorization")
                        .addHeader("Access-Control-Allow-Methods","POST, GET, DELETE, OPTIONS");

                Request request1=builder.build();
                return chain.proceed(request1);
            }
        });

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIEndPoint apiEndPoint=retrofit.create(APIEndPoint.class);

        return apiEndPoint;
    }
}

package com.example.henrasetianugraha.e_commerce.utils;

import android.util.Log;

import com.example.henrasetianugraha.e_commerce.Model.Model;
import com.example.henrasetianugraha.e_commerce.Model.ModelDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Henra Setia Nugraha on 8/25/2016.
 */

public class JSONParser {
    private static Gson gson;

    public static Model getProducts(String json) throws IOException{
        gson=new Gson();
        return gson.fromJson(json,Model.class);
    }

    public static ModelDetail getDetailProducts(String json) throws IOException{
        gson=new Gson();
        return gson.fromJson(json,ModelDetail.class);
    }
}

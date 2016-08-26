package com.example.henrasetianugraha.e_commerce.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.henrasetianugraha.e_commerce.API.ServiceManager;
import com.example.henrasetianugraha.e_commerce.Model.ModelDetail;
import com.example.henrasetianugraha.e_commerce.R;
import com.example.henrasetianugraha.e_commerce.utils.JSONParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {

    private TextView txtName;
    private TextView txtDiskon;
    private TextView txtMainPrice;
    private TextView txtSecPrice;
    private TextView txtDesc;
    private TextView txtStok;
    private ImageView imageView;
    private RatingBar ratingBar;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initView();
        reqDetailProducts();
    }

    private void initView() {
        txtName=(TextView)findViewById(R.id.txt_nama_detail);
        txtDiskon=(TextView)findViewById(R.id.txt_diskon_detail);
        txtMainPrice=(TextView)findViewById(R.id.txt_main_price_detail);
        txtSecPrice=(TextView)findViewById(R.id.txt_sec_price_detail);
        txtStok=(TextView)findViewById(R.id.txt_stok_detail);
        txtDesc=(TextView)findViewById(R.id.txt_desc_detail);
        imageView=(ImageView)findViewById(R.id.img_detail);
        ratingBar=(RatingBar)findViewById(R.id.rtg_detail);
        relativeLayout=(RelativeLayout)findViewById(R.id.rl_detail);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDesc.setVisibility(txtDesc.isShown()?View.GONE:View.VISIBLE);
            }
        });
    }

    private void reqDetailProducts() {
        String idProduct= String.valueOf(getIntent().getStringExtra("position"));
        System.out.println(getIntent().getIntExtra("position",0));
        Log.i("pos",idProduct);
        Call<JsonElement> resCall = ServiceManager.getService().getDetailProducts(idProduct);
        resCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    ModelDetail detail= JSONParser.getDetailProducts(String.valueOf(response.body()));
                    System.out.println(detail.getDATA().getNama());
                    bindData(detail.getDATA());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("response product detail", t.toString());
            }
        });
    }

    private void bindData(ModelDetail.Data data) {
        System.out.println(data.getNama());
        txtName.setText(data.getNama());
        txtDiskon.setText("Discount "+data.getDiskon()+"%");
        txtMainPrice.setText(data.getHarga());
        txtMainPrice.setText(String.valueOf(Integer.valueOf(data.getHarga())-Integer.valueOf(data.getHarga())*(Integer.valueOf(data.getDiskon())/100)));
        txtSecPrice.setText(data.getHarga());
        txtStok.setText(getIntent().getStringExtra("stok"));
        txtDesc.setText(data.getDeskripsi());
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("url")).into(imageView);
        ratingBar.setRating(Float.valueOf(getIntent().getStringExtra("rate")));
    }


}

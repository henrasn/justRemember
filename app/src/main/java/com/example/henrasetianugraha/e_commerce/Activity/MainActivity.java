package com.example.henrasetianugraha.e_commerce.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.henrasetianugraha.e_commerce.API.ServiceManager;
import com.example.henrasetianugraha.e_commerce.Adapter.KatalogAdapter;
import com.example.henrasetianugraha.e_commerce.Model.Model;
import com.example.henrasetianugraha.e_commerce.Model.ModelDetail;
import com.example.henrasetianugraha.e_commerce.R;
import com.example.henrasetianugraha.e_commerce.utils.JSONParser;
import com.example.henrasetianugraha.e_commerce.utils.RecOnClickListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Streams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayout;
    private KatalogAdapter adapter;
    private List<Model.Product> products = new ArrayList<>();
    private List<Model.Product> newProducts = new ArrayList<>();
    private CharSequence[] listFilter;
    private Integer position = 0;
    private Boolean filtered=false;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.katalog_activity);
        initView();
        reqProducts();

        recyclerView.addOnItemTouchListener(new RecOnClickListener(getApplicationContext(), recyclerView, new RecOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<Model.Product> bind=new ArrayList<>();
                if (filtered){
                    bind=newProducts;
                }else {
                    bind=products;
                }
                Intent intent=new Intent(getApplicationContext(),DetailProduct.class);
                intent.putExtra("position",bind.get(position).getId());
                intent.putExtra("url",bind.get(position).getUrl_foto());
                intent.putExtra("stok",bind.get(position).getStok());
                intent.putExtra("rate",bind.get(position).getRating());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void initView() {
        progressBar=(ProgressBar) findViewById(R.id.pb);
        recyclerView = (RecyclerView) findViewById(R.id.main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    public void btnSort(View view) {
        final CharSequence[] sorts = {"Popularity", "Low Price - High Price", "High Proce - Low Price"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setSingleChoiceItems(sorts, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position = i;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sort();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void sort() {
        Collections.sort(products, new Comparator<Model.Product>() {
            @Override
            public int compare(Model.Product product, Model.Product t1) {
                if (position == 0) {
                    return Integer.valueOf(t1.getRating()).compareTo(Integer.valueOf(product.getRating()));
                } else if (position == 1) {
                    return Integer.valueOf(product.getHarga()).compareTo(Integer.valueOf(t1.getHarga()));
                } else {
                    return Integer.valueOf(t1.getHarga()).compareTo(Integer.valueOf(product.getHarga()));
                }
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void btnFilter(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by")
                .setSingleChoiceItems(listFilter, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position = i;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        filter();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void filter() {
        filtered=true;
        String filter = String.valueOf(listFilter[position]);
        System.out.println(filter);
        newProducts.clear();
        for (Model.Product product : products) {
            if (product.getJenis() != null && product.getJenis().equals(filter)) {
                newProducts.add(product);
            }
        }
        if (filter.equals("Semua")||filter.equals("")){
            newProducts=products;
            filtered=false;
        }
        adapter = new KatalogAdapter(getApplicationContext(), newProducts);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void reqProducts() {
        Call<JsonElement> resCall = ServiceManager.getService().getProducts();
        resCall.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                String json = String.valueOf(response.body());
                try {
                    Model model = JSONParser.getProducts(json);
                    adapter = new KatalogAdapter(getApplicationContext(), model.getDATA().getProducts());
                    recyclerView.setAdapter(adapter);
                    products = model.getDATA().getProducts();
                    progressBar.setVisibility(View.GONE);
                    ArrayList<String> strings = new ArrayList<>();
                    strings.add("Semua");
                    for (Model.Filter f : model.getDATA().getFilter()) {
                        strings.add(f.getJenis());
                    }

                    listFilter = strings.toArray(new CharSequence[strings.size()]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("response product", t.toString());
            }
        });
    }


}

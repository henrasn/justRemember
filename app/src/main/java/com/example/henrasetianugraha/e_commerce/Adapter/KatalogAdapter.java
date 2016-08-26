package com.example.henrasetianugraha.e_commerce.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.henrasetianugraha.e_commerce.Model.Model;
import com.example.henrasetianugraha.e_commerce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henra Setia Nugraha on 8/25/2016.
 */

public class KatalogAdapter  extends RecyclerView.Adapter<KatalogAdapter.KatalogViewHolder>{

    private List<Model.Product> products=new ArrayList<>();
    private Context context;

    public KatalogAdapter(Context context, List<Model.Product> products) {
        this.products=products;
        this.context=context;
    }

    @Override
    public KatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new KatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KatalogViewHolder holder, int position) {
        String url=products.get(position).getUrl_foto();
        System.out.println(url);
        Picasso.with(context).load(url).into(holder.imageView);
        holder.txtName.setText(products.get(position).getNama());
        holder.txtDiscount.setText("Discount "+products.get(position).getDiskon()+"%");
        holder.txtPrice.setText(products.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    protected class KatalogViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView txtName;
        TextView txtDiscount;
        TextView txtPrice;
        public KatalogViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.img_catalog_main);
            txtName=(TextView)itemView.findViewById(R.id.txt_name_catalog_main);
            txtDiscount=(TextView)itemView.findViewById(R.id.txt_discount_catalog_main);
            txtPrice=(TextView)itemView.findViewById(R.id.txt_price_catalog_main);
        }
    }
}

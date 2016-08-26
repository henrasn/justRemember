package com.example.henrasetianugraha.e_commerce.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henra Setia Nugraha on 8/25/2016.
 */

public class Model {
    private String STATUS;
    private String STATUS_CODE;
    private message MESSAGE;
    private data DATA;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(String STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public message getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(message MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public data getDATA() {
        return DATA;
    }

    public void setDATA(data DATA) {
        this.DATA = DATA;
    }

    public class data {

        private List<Filter> filter;
        private List<Product> products;

        public List<Filter> getFilter() {
            return filter;
        }

        public void setFilter(List<Filter> filter) {
            this.filter = filter;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }
    }

    public class Filter {

        private String jenis;

        public String getJenis() {
            return jenis;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }
    }

    public class Product {

        private String id;
        private String rating;
        private String nama;
        private String jenis;
        private String harga;
        private String diskon;
        private String stok;
        private String url_foto;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getJenis() {
            return jenis;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }

        public String getDiskon() {
            return diskon;
        }

        public void setDiskon(String diskon) {
            this.diskon = diskon;
        }

        public String getStok() {
            return stok;
        }

        public void setStok(String stok) {
            this.stok = stok;
        }

        public String getUrl_foto() {
            return url_foto;
        }

        public void setUrl_foto(String url_foto) {
            this.url_foto = url_foto;
        }
    }

    public class message {

        private String PROD;
        private String DEVEL;

        public String getPROD() {
            return PROD;
        }

        public void setPROD(String PROD) {
            this.PROD = PROD;
        }

        public String getDEVEL() {
            return DEVEL;
        }

        public void setDEVEL(String DEVEL) {
            this.DEVEL = DEVEL;
        }
    }
}

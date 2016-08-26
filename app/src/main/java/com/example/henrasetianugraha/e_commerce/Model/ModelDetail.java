package com.example.henrasetianugraha.e_commerce.Model;

import java.util.List;

/**
 * Created by Henra Setia Nugraha on 8/25/2016.
 */

public class ModelDetail {
    private Boolean STATUS;
    private Integer STATUS_CODE;
    private Message MESSAGE;
    private Data DATA;

    public Boolean getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Boolean STATUS) {
        this.STATUS = STATUS;
    }

    public Integer getSTATUS_CODE() {
        return STATUS_CODE;
    }

    public void setSTATUS_CODE(Integer STATUS_CODE) {
        this.STATUS_CODE = STATUS_CODE;
    }

    public Message getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(Message MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public Data getDATA() {
        return DATA;
    }

    public void setDATA(Data DATA) {
        this.DATA = DATA;
    }

    public class Message{
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

    public class Ukuran{
        public String available;

        public String getAvailable() {
            return available;
        }

        public void setAvailable(String available) {
            this.available = available;
        }
    }

    public class Data{
        public String nama;
        public String harga;
        public String diskon;
        public String deskripsi;
        public String spesifikasi;
        public List<Ukuran> ukuran;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
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

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
        }

        public String getSpesifikasi() {
            return spesifikasi;
        }

        public void setSpesifikasi(String spesifikasi) {
            this.spesifikasi = spesifikasi;
        }

        public List<Ukuran> getUkuran() {
            return ukuran;
        }

        public void setUkuran(List<Ukuran> ukuran) {
            this.ukuran = ukuran;
        }
    }
}

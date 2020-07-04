package com.hafilah.ta_restoran;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tMenu") //Membuat tabel baru dengan nama "menu"
public class Menu implements Serializable {

    //Membuat kolom Nomor sebagai Primary Key
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "nomor_meja")
    protected
    String no;

    //Membuat kolom Nama
    @ColumnInfo(name = "nama_pelanggan")
    protected
    String nama;

    //Membuat kolom pesanan
    @ColumnInfo(name = "nama_pesanan")
    protected
    String pesanan;

    //Method untuk mengambil data NIM
    @NonNull
    public String getNomor() {
        return no;
    }

    //Method untuk memasukan data NIM
    public void setNomor(@NonNull String no) {
        this.no = no;
    }

    //Method untuk mengambil data Nama
    public String getNama() {
        return nama;
    }

    //Method untuk memasukan data Nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    //Method untuk mengambil data pesanan
    public String getPesanan() {
        return pesanan;
    }

    //Method untuk memasukan data pesanan
    public void setPesanan(String pesanan) {
        this.pesanan = pesanan;
    }

}
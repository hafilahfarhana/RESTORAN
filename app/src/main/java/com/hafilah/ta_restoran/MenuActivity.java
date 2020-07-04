package com.hafilah.ta_restoran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Nomor, Nama, Pesanan;
    private Button btnSave, btnLihat;

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Nomor = findViewById(R.id.editnomeja);
        Nama = findViewById(R.id.editnama);
        Pesanan = findViewById(R.id.editpesananan);

        btnSave = findViewById(R.id.save);
        btnSave.setOnClickListener(this);
        btnLihat = findViewById(R.id.lihat);
        btnLihat.setOnClickListener(this);

        //Inisialisasi dan memanggil Room Database
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbMenu") //Nama File Database yang akan disimpan
                .build();
    }

    //Menjalankan method Insert Data
    @SuppressLint("StaticFieldLeak")
    private void insertData(final Menu menu) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.menuDAO().insertMenu(menu);
            }

            @Override
            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(MenuActivity.this, "Status Row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onClick(View vi) {
        switch (vi.getId()) {
            case R.id.save:

                //Mengecek Data Nomor dan Nama
                if (Nomor.getText().toString().isEmpty() || Nama.getText().toString().isEmpty()) {
                    Toast.makeText(MenuActivity.this, "Nomor atau Nama TIdak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    //Membuat Instance/Objek Dari Class Entity menu
                    Menu data = new Menu();

                    //Memasukan data yang diinputkan user pada database
                    data.setNomor(Nomor.getText().toString());
                    data.setNama(Nama.getText().toString());
                    data.setPesanan(Pesanan.getText().toString());
                    insertData(data);

                    //Mengembalikan EditText menjadi seperti semula\
                    Nomor.setText("");
                    Nama.setText("");
                    Pesanan.setText("");
                }
                break;

            case R.id.lihat:
                startActivity(new Intent(MenuActivity.this, ReadDataMenu.class));
                break;
        }
    }

}

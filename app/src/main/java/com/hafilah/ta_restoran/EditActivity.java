package com.hafilah.ta_restoran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditActivity extends AppCompatActivity {

    //Deklarasi Variable
    private EditText Nama, Pesanan;
    private AppDatabase database;
    private Button bSimpan;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Nama = findViewById(R.id.editnama);
        Pesanan = findViewById(R.id.editpesananan);
        bSimpan = findViewById(R.id.save);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbMenu").build();

        //Menampilkan data menu yang akan di edit
        getDataMahasiswa();

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.setNama(Nama.getText().toString());
                menu.setPesanan(Pesanan.getText().toString());
                updateData(menu);
            }
        });
    }


    //Method untuk menapilkan data menu yang akan di edit
    private void getDataMahasiswa(){
        //Mendapatkan data dari Intent
        menu = (Menu)getIntent().getSerializableExtra("data");

        Nama.setText(menu.getNama());
        Pesanan.setText(menu.getPesanan());


    }

    //Menjalankan method Update Data
    @SuppressLint("StaticFieldLeak")
    private void updateData(final Menu menu){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.menuDAO().updateMenu(menu);
            }

            @Override
            protected void onPostExecute(Integer status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(EditActivity.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this, ReadDataMenu.class));
                finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditActivity.this, ReadDataMenu.class));
        finish();
    }
}
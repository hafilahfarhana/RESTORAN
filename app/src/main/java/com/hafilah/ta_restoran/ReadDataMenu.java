package com.hafilah.ta_restoran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadDataMenu extends AppCompatActivity {

    //Deklarasi Variable
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Menu> daftarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_menu);
        getSupportActionBar().setTitle("Daftar Menu");

        //Inisialisasi ArrayList
        daftarMenu = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbMenu").allowMainThreadQueries().build();

        /*
         * Mengambil data Menu dari Database
         * lalu memasukannya ke kedalam ArrayList (daftarMenu)
         */
        daftarMenu.addAll(Arrays.asList(database.menuDAO().readDataMenu()));

        recyclerView = findViewById(R.id.dataItem);

        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RecyclerMenuAdapter(daftarMenu, ReadDataMenu.this);
        recyclerView.setAdapter(adapter);
    }
}
package com.hafilah.ta_restoran;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Menu.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Untuk mengakses Database menggunakan method abstract
    public abstract MenuDAO menuDAO();
}

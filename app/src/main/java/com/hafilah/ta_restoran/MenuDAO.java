package com.hafilah.ta_restoran;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MenuDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMenu(Menu menu);

    @Query("SELECT * FROM tMenu")
    Menu[] readDataMenu();

    @Update
    int updateMenu(Menu menu);

    @Delete
    void deleteMenu(Menu menu);

    @Query("SELECT * FROM tMenu WHERE nomor_meja = :nomor LIMIT 1")
    Menu selectDetailMenu(String nomor);
}
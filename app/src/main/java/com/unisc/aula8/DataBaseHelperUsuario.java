package com.unisc.aula8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelperUsuario extends SQLiteOpenHelper {
    public DataBaseHelperUsuario(@Nullable Context context) {
        super(context, "carro.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuario(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "usuario TEXT," +
                "senha TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

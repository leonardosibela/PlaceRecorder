package com.siblea.placerecorder.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseManager extends SQLiteOpenHelper {

    private static final String BASE_NAME = "placerecorder.db";
    private static final int BASE_VERION = 1;

    DatabaseManager(Context context) {
        super(context, BASE_NAME, null, BASE_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String placeTableDDL =
                "CREATE TABLE place (" +
                        "id TEXT PRIMARY KEY, " +
                        "name TEXT NOT NULL, " +
                        "description TEXT NOT NULL, " +
                        "lat TEXT, " +
                        "lng INTEGER)";

        db.execSQL(placeTableDDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

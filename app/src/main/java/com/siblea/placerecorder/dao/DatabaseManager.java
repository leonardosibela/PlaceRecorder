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
                        "id REAL PRIMARY KEY, " +
                        "name TEXT NOT NULL, " +
                        "description TEXT NOT NULL, " +
                        "lat REAL, " +
                        "lng REAL)";

        db.execSQL(placeTableDDL);

        db.execSQL("insert into place (id, name, description, lat, lng) values (1, 'Place 1', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (2, 'Place 2', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (3, 'Place 3', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (4, 'Place 4', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (5, 'Place 5', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (6, 'Place 6', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (7, 'Place 7', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (8, 'Place 8', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (9, 'Place 9', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (10, 'Place 10', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (11, 'Place 11', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (12, 'Place 12', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (13, 'Place 13', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (14, 'Place 14', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (15, 'Place 15', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (16, 'Place 16', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (17, 'Place 17', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (18, 'Place 18', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (19, 'Place 19', 'Loren place ipsum', -47.00000, -25.5664564);");
        db.execSQL("insert into place (id, name, description, lat, lng) values (20, 'Place 20', 'Loren place ipsum', -47.00000, -25.5664564);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

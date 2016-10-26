package com.siblea.placerecorder.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.siblea.placerecorder.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceDao {

    private SQLiteDatabase sqLiteDatabase;
    private DatabaseManager dbManager;

    private static final String TABLE = "place";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LAT = "lat";
    private static final String LNG = "lng";

    private static final String[] ALL_COLUMNS = {ID, NAME, DESCRIPTION, LAT, LNG};

    public PlaceDao(Context context) {
        dbManager = new DatabaseManager(context);
    }

    public void open() throws android.database.SQLException {
        sqLiteDatabase = dbManager.getWritableDatabase();
    }

    public void close() {
        dbManager.close();
    }

    public List<Place> selectAll() {
        List<Place> books = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(TABLE, ALL_COLUMNS, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Place place = cursorToPlace(cursor);
            books.add(place);
            cursor.moveToNext();
        }

        cursor.close();
        return books;
    }

    public boolean insert(Place place) {
        ContentValues values = placeToContentValues(place);
        long rowId = sqLiteDatabase.insert(TABLE, null, values);
        return rowId != -1;
    }

    private ContentValues placeToContentValues(Place place) {

        ContentValues values = new ContentValues();

        values.put(NAME, place.getName());
        values.put(DESCRIPTION, place.getDescription());
        values.put(LAT, place.getLat());
        values.put(LNG, place.getLng());

        return values;
    }

    private Place cursorToPlace(Cursor cursor) {
        Place place = new Place();

        place.setId(cursor.getInt(cursor.getColumnIndex(ID)));
        place.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        place.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        place.setLat(cursor.getDouble(cursor.getColumnIndex(LAT)));
        place.setLng(cursor.getDouble(cursor.getColumnIndex(LNG)));

        return place;
    }
}

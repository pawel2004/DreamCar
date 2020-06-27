package com.example.grybos.dreamcar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {


    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE cars (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'path' TEXT, 'name' TEXT, 'year' TEXT, 'power' TEXT, 'engine' TEXT, 'price' TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS cars");
        onCreate(db);

    }

    public boolean insert(String path, String name, String year, String power, String engine, String price){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("path", path);
        contentValues.put("name", name);
        contentValues.put("year", year);
        contentValues.put("power", power);
        contentValues.put("engine", engine);
        contentValues.put("price", price);

        db.insertOrThrow("cars", null, contentValues);
        db.close();
        return true;
    }

    public ArrayList<Record> getAll(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Record> records = new ArrayList<>();
        Cursor result = db.rawQuery("SELECT * FROM cars", null);
        while (result.moveToNext()){

            records.add(new Record(
                    result.getString(result.getColumnIndex("_id")),
                    result.getString(result.getColumnIndex("path")),
                    result.getString(result.getColumnIndex("name")),
                    result.getString(result.getColumnIndex("year")),
                    result.getString(result.getColumnIndex("power")),
                    result.getString(result.getColumnIndex("engine")),
                    result.getString(result.getColumnIndex("price"))

            ));

        }
        return records;
    }

    public int delete(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("cars", "_id = ?", new String[]{id});

    }
}

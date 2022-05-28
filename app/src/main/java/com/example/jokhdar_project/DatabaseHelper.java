package com.example.jokhdar_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String DATABASE_NAME = "Student";
    public static final String TABLE_NAME = "student";
    public static final String COLUMN_ID = "stdID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_NATID = "natID";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_GENDER = "gender";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        sqLiteDatabase.execSQL
                (
                        "CREATE TABLE " + TABLE_NAME
                                + "("
                                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                                + COLUMN_NAME + " TEXT NOT NULL,"
                                + COLUMN_SURNAME +" TEXT NOT NULL,"
                                + COLUMN_FNAME +" TEXT NOT NULL,"
                                + COLUMN_NATID +" TEXT NOT NULL,"
                                + COLUMN_DOB +" TEXT NOT NULL,"
                                + COLUMN_GENDER +" TEXT NOT NULL"
                                + ")"
                );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }


    public void addStudent(String id, String name, String surname, String fname, String natID, String dob, String gender)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SURNAME, surname);
        values.put(COLUMN_FNAME, fname);
        values.put(COLUMN_NATID, natID);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_GENDER, gender);

        db.insert(TABLE_NAME, null, values);
    }

    public void deleteStudent(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_ID+" = ?", new String[] {id});
    }

    public void updateData(String id, String name, String surname, String fname, String natID, String dob, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(COLUMN_FNAME, fname);
        cv.put(COLUMN_NATID, natID);
        cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_GENDER, gender);

        db.update(TABLE_NAME, cv, COLUMN_ID+" = ?", new String[]{id});
    }

    public Cursor viewStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return x;
    }
}

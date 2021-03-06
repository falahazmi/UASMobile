package com.example.py7.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_login";
    public static final String table_name = "table_login";

    public static final String row_id = "_id";
    public static final String row_nama="Nama";
    public static final String row_email = "Email";
    public static final String row_password = "Password";
    public  static final String row_instansi = "Instansi";
    public static final String row_stpekerjaan = "StatusPekerjaan";
    public static final String row_noTlpn = "NomorTelepon";


    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 5);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "("
                + row_id + " INTERGER PRIMARY KEY AUTOINCREMENT,"
                + row_nama + "TEXT,"
                + row_email + " TEXT,"
                + row_password + " TEXT,"
                + row_instansi + "TEXT,"
                + row_stpekerjaan + "TEXT"
                + row_noTlpn + "TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }


    public boolean checkUser(String email, String password){
        String[] columns = {row_id};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_email + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {email,password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}

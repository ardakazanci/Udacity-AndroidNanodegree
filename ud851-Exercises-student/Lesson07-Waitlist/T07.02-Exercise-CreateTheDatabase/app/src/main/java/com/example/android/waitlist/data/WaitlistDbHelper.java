package com.example.android.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// TODO (COMPLETED) extend the SQLiteOpenHelper class
public class WaitlistDbHelper extends SQLiteOpenHelper {

    // Veritabanı Adı.
    public static final String DATABASE_NAME = "waitlist.db";
    // Veritabanı Sürüm numarası.
    public static final int DATABASE_VERSION = 1;

    // Constructor - SQLiteOpenHelper ' a ilgili değerler gönderiliyor.
    public WaitlistDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Veritabanı ilk kez oluşturulduğunda çağrılır.
    // İlk kez oluşturulduğunda Table ı da oluşturuyoruz.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " +
                WaitlistContract.WaitlistEntry.TABLE_NAME + " (" +
                WaitlistContract.WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    // Versiyon numarası yükseltildiğinde çalışacak metot.
    // Eğer ki ileride yeni bir sütun vs. ekler isek bu metot devreye girmesi için versiyon numarasının arttırılması gerekir.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Eskisi kaldır yeniden oluştur.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WaitlistContract.WaitlistEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    // TODO (COMPLETED) Create a static final String called DATABASE_NAME and set it to "waitlist.db"

    // TODO (COMPLETED) Create a static final int called DATABASE_VERSION and set it to 1

    // TODO (COMPLETED) Create a Constructor that takes a context and calls the parent constructor


    // TODO (COMPLETED) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table

    // TODO (COMPLETED) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE

    // TODO (COMPLETED) Override the onUpgrade method

    // TODO (COMPLETED) Inside, execute a drop table query, and then call onCreate to re-create it

}
package com.adityaladwa.contentprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AdityaLadwa on 28-Jul-15.
 */
public class DBhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "list.db";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_LIST_TABLE_QUERY =
                "CREATE TABLE " + Contract.ListEntry.TABLE_NAME + " (" +
                        Contract.ListEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Contract.ListEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                        Contract.ListEntry.COLUMN_DETAIL + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_LIST_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + Contract.ListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

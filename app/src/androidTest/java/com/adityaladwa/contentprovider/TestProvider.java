package com.adityaladwa.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.adityaladwa.contentprovider.data.Contract;
import com.adityaladwa.contentprovider.data.DBhelper;
import com.adityaladwa.contentprovider.models.ListModel;


public class TestProvider extends AndroidTestCase {
    public void testDeleteDB() throws Throwable {
        mContext.deleteDatabase(Contract.ListEntry.TABLE_NAME);
    }

    public void testInsertReadProvider() throws Throwable {
        ListModel item = new ListModel();

        item.setmTitle("Aditya");
        item.setmDetail("He is doing android development");

        DBhelper dBhelper = new DBhelper(mContext);
        SQLiteDatabase db = dBhelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contract.ListEntry.COLUMN_TITLE, item.getmTitle());
        values.put(Contract.ListEntry.COLUMN_DETAIL, item.getmDetail());

        long idrow = db.insert(Contract.ListEntry.TABLE_NAME, null, values);
        assertTrue(idrow != -1);

        ListModel item2 = new ListModel();

        item2.setmTitle("Aditya");
        item2.setmDetail("What is he doing");

        ContentValues values2 = new ContentValues();
        values2.put(Contract.ListEntry.COLUMN_TITLE, item.getmTitle());
        values2.put(Contract.ListEntry.COLUMN_DETAIL, item.getmDetail());

        long idrow2 = db.insert(Contract.ListEntry.TABLE_NAME, null, values2);
        assertTrue(idrow2 != -1);


        Log.d("DATABASE", "Row id is" + idrow);


//        Cursor listCursor = mContext.getContentResolver().query(Contract.ListEntry.CONTENT_URI,
//                null,
//                null,
//                null,
//                null);

        Cursor listCursor = mContext.getContentResolver().query(Contract.ListEntry.buildListUriWithName("Aditya"),
                null,
                null,
                null,
                null);


        if (listCursor.moveToFirst()) {
            int titleIndex = listCursor.getColumnIndex(Contract.ListEntry.COLUMN_TITLE);
            String title = listCursor.getString(titleIndex);

            int detailindex = listCursor.getColumnIndex(Contract.ListEntry.COLUMN_DETAIL);
            String detail = listCursor.getString(detailindex);
            Log.d("Content ", title + " " + detail);
        } else {
            fail("Cant read the data back");
        }

    }

    public void testGetType() {
        String type = mContext.getContentResolver().getType(Contract.ListEntry.CONTENT_URI);
        assertEquals(Contract.ListEntry.CONTENT_DIR, type);

        String type2 = mContext.getContentResolver().getType(Contract.ListEntry.buildListUriWithName("Aditya"));
        assertEquals(Contract.ListEntry.CONTENT_DIR, type2);
        Log.d("DATABASE", Contract.ListEntry.buildListUriWithName("Aditya").toString());

    }
}

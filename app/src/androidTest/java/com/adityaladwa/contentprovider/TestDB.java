package com.adityaladwa.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.adityaladwa.contentprovider.data.Contract;
import com.adityaladwa.contentprovider.data.DBhelper;
import com.adityaladwa.contentprovider.models.ListModel;

/**
 * Created by AdityaLadwa on 28-Jul-15.
 */
public class TestDB extends AndroidTestCase {
    public void testCreateDb() throws Throwable {
        mContext.deleteDatabase(Contract.ListEntry.TABLE_NAME);

        SQLiteDatabase db = new DBhelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertReadDb() throws Throwable {
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
        Log.v("DATABASE", "Row id is" + idrow);


        Cursor listCursor = db.query(
                Contract.ListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if (listCursor.moveToFirst()) {
            int titleIndex = listCursor.getColumnIndex(Contract.ListEntry.COLUMN_TITLE);
            String title = listCursor.getString(titleIndex);

            int detailindex = listCursor.getColumnIndex(Contract.ListEntry.COLUMN_DETAIL);
            String detail = listCursor.getString(detailindex);

        } else {
            fail("Cant read the data back");
        }

    }
}

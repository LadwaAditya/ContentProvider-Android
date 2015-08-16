package com.adityaladwa.contentprovider.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by AdityaLadwa on 28-Jul-15.
 */
public class ListProvider extends ContentProvider {

    private static final int LIST = 100;
    private static final int LIST_TITLE = 101;
    private DBhelper mDBhelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();


    private static UriMatcher buildUriMatcher() {
        final String authority = Contract.CONTENT_AUTHORITY;
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(authority, Contract.PATH_LIST, LIST);
        matcher.addURI(authority, Contract.PATH_LIST + "/*", LIST_TITLE);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDBhelper = new DBhelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case LIST: {
                retCursor = mDBhelper.getReadableDatabase()
                        .query(Contract.ListEntry.TABLE_NAME,
                                columns,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            }

            case LIST_TITLE: {
                retCursor = mDBhelper.getReadableDatabase()
                        .query(Contract.ListEntry.TABLE_NAME,
                                columns,
                                Contract.ListEntry.COLUMN_TITLE + " = '" + uri.getPathSegments().get(1) + "' ",
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);

        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case LIST:
                return Contract.ListEntry.CONTENT_DIR;
            case LIST_TITLE:
                return Contract.ListEntry.CONTENT_DIR;
            default:
                throw new UnsupportedOperationException("Unknown Uri " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}

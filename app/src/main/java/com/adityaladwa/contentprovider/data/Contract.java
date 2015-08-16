package com.adityaladwa.contentprovider.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by AdityaLadwa on 28-Jul-15.
 */
public class Contract {

    public static final String CONTENT_AUTHORITY = "com.adityaladwa.contentprovider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_LIST = "list";

    public static final class ListEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_LIST).build();

        public static final String CONTENT_DIR = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_LIST;
        public static final String CONTENT_ITEM = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_LIST;


        public static final String TABLE_NAME = "list";
        public static final String ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DETAIL = "detail";

        public static Uri buildListUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildListUriWithName(String title) {
            return CONTENT_URI.buildUpon().appendPath(title).build();
        }
    }
}

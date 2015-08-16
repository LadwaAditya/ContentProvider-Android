package com.adityaladwa.contentprovider.models;

/**
 * Created by AdityaLadwa on 28-Jul-15.
 */
public class ListModel {

    private String mTitle;
    private String mDetail;

    public ListModel() {
    }

    public ListModel(String mTitle, String mDetail) {
        this.mTitle = mTitle;
        this.mDetail = mDetail;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDetail() {
        return mDetail;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }


}

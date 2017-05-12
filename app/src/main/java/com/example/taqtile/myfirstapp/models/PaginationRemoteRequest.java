package com.example.taqtile.myfirstapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/8/17.
 */

public class PaginationRemoteRequest {

    @SerializedName("page")
    private int page;

    @SerializedName("window")
    private int window;


    public PaginationRemoteRequest(){}

    public PaginationRemoteRequest(int page, int window) {
        this.page = page;
        this.window = window;
    }

    public int getPage() {
        return page;
    }

    public int getWindow() {
        return window;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setWindow(int window) {
        this.window = window;
    }
}

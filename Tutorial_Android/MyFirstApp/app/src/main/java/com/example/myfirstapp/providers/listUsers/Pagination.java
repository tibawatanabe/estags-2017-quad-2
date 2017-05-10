package com.example.myfirstapp.providers.listUsers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/6/17.
 */

public class Pagination {

    @SerializedName("page")
    private int page;

    @SerializedName("window")
    private int window;

    public Pagination(int page, int window) {
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

package com.example.myfirstapp.models.listUsers.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/5/17.
 */

public class ListUsersPaginationRemoteResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("window")
    private int window;
    @SerializedName("total")
    private int total;
    @SerializedName("totalPages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public int getWindow() {
        return window;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

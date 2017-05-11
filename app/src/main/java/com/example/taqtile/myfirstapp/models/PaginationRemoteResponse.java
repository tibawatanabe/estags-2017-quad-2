package com.example.taqtile.myfirstapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/8/17.
 */

public class PaginationRemoteResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("window")
    private int window;

    @SerializedName("total")
    private int total;

    @SerializedName("totalPages")
    private int totalPages;


    //Constructor
    public PaginationRemoteResponse(){}

    public PaginationRemoteResponse(int page, int window, int total, int totalPages) {
        this.page = page;
        this.window = window;
        this.total = total;
        this.totalPages = totalPages;
    }

    //Getters
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

    //Setters
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

package com.example.taqtile.myfirstapp.models.listUsers;

import com.example.taqtile.myfirstapp.models.PaginationRemoteResponse;
import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/8/17.
 */

public class ListUsersRemoteResponse {

    @SerializedName("data")
    List<UserRemoteResponse>listUsersDataRemoteResponse = new ArrayList<UserRemoteResponse>();

    @SerializedName("pagination")
    private PaginationRemoteResponse pagination;

    public ListUsersRemoteResponse(List<UserRemoteResponse> listUsersDataRemoteResponse, PaginationRemoteResponse pagination) {
        this.listUsersDataRemoteResponse = listUsersDataRemoteResponse;
        this.pagination = pagination;
    }

    public List<UserRemoteResponse> getListUsersDataRemoteResponse() {
        return listUsersDataRemoteResponse;
    }

    public PaginationRemoteResponse getPagination() {
        return pagination;
    }

    public void setListUsersDataRemoteResponse(List<UserRemoteResponse> listUsersDataRemoteResponse) {
        this.listUsersDataRemoteResponse = listUsersDataRemoteResponse;
    }

    public void setPagination(PaginationRemoteResponse pagination) {
        this.pagination = pagination;
    }
}

package com.example.myfirstapp.models.listUsers.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/5/17.
 */

public class ListUsersRemoteResponse {

    @SerializedName("data")
    private List<ListUsersDataRemoteResponse> data = new ArrayList<ListUsersDataRemoteResponse>();
    @SerializedName("pagination")
    private ListUsersPaginationRemoteResponse pagination;

    public ListUsersRemoteResponse (ListUsersRemoteResponse listUsersRemoteResponse) {
        this.data = listUsersRemoteResponse.getData();
        this.pagination = listUsersRemoteResponse.getPagination();
    }

    public List<ListUsersDataRemoteResponse> getData() {
        return data;
    }

    public ListUsersPaginationRemoteResponse getPagination() {
        return pagination;
    }

    public void setData(List<ListUsersDataRemoteResponse> data) {
        this.data = data;
    }

    public void setPagination(ListUsersPaginationRemoteResponse pagination) {
        this.pagination = pagination;
    }
}

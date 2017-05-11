package com.example.taqtile.myfirstapp.providers.listUsers;


import com.example.taqtile.myfirstapp.models.PaginationRemoteRequest;
import com.example.taqtile.myfirstapp.models.PaginationRemoteResponse;
import com.example.taqtile.myfirstapp.models.listUsers.ListUsersRemoteResponse;
import com.example.taqtile.myfirstapp.providers.RetrofitHelper;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/3/17.
 */

public class ListUsersProvider {

    private ListUsersServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public void performRequest(String token, PaginationRemoteRequest pagination, Callback<ListUsersRemoteResponse> listUsersRemoteResponseCallback) {
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(ListUsersServices.class);

        String serializedPagination = new Gson().toJson(pagination);

        Call<ListUsersRemoteResponse> call = services.listUsers(token, serializedPagination);
        call.enqueue(listUsersRemoteResponseCallback);
    }
}

package com.example.taqtile.myfirstapp.providers.getUser;

import com.example.taqtile.myfirstapp.models.PaginationRemoteRequest;
import com.example.taqtile.myfirstapp.models.UserDataRemoteResponse;
import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.example.taqtile.myfirstapp.models.listUsers.ListUsersRemoteResponse;
import com.example.taqtile.myfirstapp.providers.RetrofitHelper;
import com.example.taqtile.myfirstapp.providers.listUsers.ListUsersServices;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/12/17.
 */

public class GetUserProvider {

    private GetUserServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public void performRequest(String token, int id, Callback<UserDataRemoteResponse> userDataRemoteResponseCallback) {
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(GetUserServices.class);

        Call<UserDataRemoteResponse> call = services.getUser(token, id);
        call.enqueue(userDataRemoteResponseCallback);
    }
}

package com.example.taqtile.myfirstapp.providers.deleteUser;

import com.example.taqtile.myfirstapp.models.PaginationRemoteRequest;
import com.example.taqtile.myfirstapp.models.deleteUser.DeleteUserRemoteResponse;
import com.example.taqtile.myfirstapp.models.listUsers.ListUsersRemoteResponse;
import com.example.taqtile.myfirstapp.providers.RetrofitHelper;
import com.example.taqtile.myfirstapp.providers.listUsers.ListUsersServices;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/11/17.
 */

public class DeleteUserProvider {
    private DeleteUserServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public void performRequest(String token, int id, Callback<DeleteUserRemoteResponse> deleteUserRemoteResponseCallback) {
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(DeleteUserServices.class);

        Call<DeleteUserRemoteResponse> call = services.deleteUser(token, id);
        call.enqueue(deleteUserRemoteResponseCallback);
    }
}

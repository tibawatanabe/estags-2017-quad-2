package com.example.taqtile.myfirstapp.providers.createUser;

import com.example.taqtile.myfirstapp.models.createUser.CreateUserRemoteResponse;
import com.example.taqtile.myfirstapp.models.createUser.CreateUserRemoteRequest;
import com.example.taqtile.myfirstapp.providers.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/10/17.
 */

public class CreateUserProvider {
    private CreateUserServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public void performRequest(String token, CreateUserRemoteRequest createUserRemoteRequest, Callback<CreateUserRemoteResponse> createUserRemoteResponseCallback) {
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(CreateUserServices.class);

        Call<CreateUserRemoteResponse> call = services.create(token, createUserRemoteRequest);
        call.enqueue(createUserRemoteResponseCallback);
    }
}

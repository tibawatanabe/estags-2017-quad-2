package com.example.myfirstapp.providers.createUser;

import com.example.myfirstapp.models.createUser.request.CreateUserRemoteRequest;
import com.example.myfirstapp.models.createUser.response.CreateUserRemoteResponse;
import com.example.myfirstapp.providers.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/10/17.
 */

public class CreateUserProvider {

    private CreateUserService services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public void performRequest(String token, CreateUserRemoteRequest createUserRemoteRequest,
                               Callback<CreateUserRemoteResponse> createUserRemoteResponseCallback){
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(CreateUserService.class);

        Call<CreateUserRemoteResponse> call = services.createUser(token, createUserRemoteRequest);
        call.enqueue(createUserRemoteResponseCallback);
    }

}

package com.example.taqtile.myfirstapp.providers.signIn;


import com.example.taqtile.myfirstapp.models.signIn.SignInRemoteRequest;
import com.example.taqtile.myfirstapp.models.signIn.SignInRemoteResponse;
import com.example.taqtile.myfirstapp.providers.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInProvider {

    private SignInServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public void performRequest(SignInRemoteRequest signInRemoteRequest, Callback<SignInRemoteResponse> signInRemoteResponseCallBack) {
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(SignInServices.class);

        Call<SignInRemoteResponse> call = services.signIn(signInRemoteRequest);
        call.enqueue(signInRemoteResponseCallBack);
    }
}

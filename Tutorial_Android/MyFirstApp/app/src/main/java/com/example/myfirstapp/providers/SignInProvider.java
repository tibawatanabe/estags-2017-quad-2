package com.example.myfirstapp.providers;


import com.example.myfirstapp.models.signIn.request.SignInRemoteRequest;
import com.example.myfirstapp.models.signIn.response.SignInRemoteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInProvider {

  private SignInServices services;
  private static Retrofit retrofit;

  public static Retrofit getRetrofit(){
    return retrofit;
  }

  public void performRequest(SignInRemoteRequest signInRemoteRequest, Callback<SignInRemoteResponse> signInRemoteResponseCallBack){
    retrofit = RetrofitHelper.buildRetrofit();
    services = retrofit.create(SignInServices.class);

    Call<SignInRemoteResponse> call = services.signIn(signInRemoteRequest);
    call.enqueue(signInRemoteResponseCallBack);


    /*
    call.enqueue(new Callback<SignInRemoteResponse>() {
      @Override
      public void onResponse(Call<SignInRemoteResponse> call, Response<SignInRemoteResponse> response) {
        SignInRemoteResponse signInResponse = response.body();
        int a = 2;
      }

      @Override
      public void onFailure(Call<SignInRemoteResponse> call, Throwable t) {
        int a = 2;
      }
    });
    */
  }
}

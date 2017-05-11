package com.example.taqtile.myfirstapp.providers.signIn;

import com.example.taqtile.myfirstapp.models.signIn.SignInRemoteRequest;
import com.example.taqtile.myfirstapp.models.signIn.SignInRemoteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by taqtile on 5/3/17.
 */

public interface SignInServices {

  @POST("authenticate")
  Call<SignInRemoteResponse> signIn(@Body SignInRemoteRequest signInRemoteRequest);

}

package com.example.myfirstapp.providers.signIn;


import com.example.myfirstapp.models.signIn.request.SignInRemoteRequest;
import com.example.myfirstapp.models.signIn.response.SignInRemoteResponse;

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

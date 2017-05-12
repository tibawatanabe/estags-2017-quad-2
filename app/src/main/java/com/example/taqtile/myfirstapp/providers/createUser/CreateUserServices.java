package com.example.taqtile.myfirstapp.providers.createUser;

import com.example.taqtile.myfirstapp.models.createUser.CreateUserRemoteRequest;
import com.example.taqtile.myfirstapp.models.UserDataRemoteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by taqtile on 5/10/17.
 */

public interface CreateUserServices {
    @POST("user")
    Call<UserDataRemoteResponse> create(@Header("Authorization") String token, @Body CreateUserRemoteRequest createUserRemoteRequest);
}

package com.example.taqtile.myfirstapp.providers.getUser;

import com.example.taqtile.myfirstapp.models.UserDataRemoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by taqtile on 5/12/17.
 */

public interface GetUserServices {
    @GET("user/{id}")
    Call<UserDataRemoteResponse> getUser(@Header("Authorization") String token, @Path("id") int id);

}

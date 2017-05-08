package com.example.myfirstapp.providers.listUsers;

import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by taqtile on 5/5/17.
 */

public interface ListUsersServices {

    @GET("users")
    Call<ListUsersRemoteResponse> listUsers(@Header("Authorization") String token, @Query("pagination") String pagination);

}

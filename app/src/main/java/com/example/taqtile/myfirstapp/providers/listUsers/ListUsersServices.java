package com.example.taqtile.myfirstapp.providers.listUsers;

import com.example.taqtile.myfirstapp.models.listUsers.ListUsersRemoteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by taqtile on 5/3/17.
 */

public interface ListUsersServices {

  @GET("users")
  Call<ListUsersRemoteResponse> listUsers(@Header("Authorization") String token, @Query("pagination") String pagination);

}

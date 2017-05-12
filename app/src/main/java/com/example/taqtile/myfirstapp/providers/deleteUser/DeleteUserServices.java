package com.example.taqtile.myfirstapp.providers.deleteUser;

import com.example.taqtile.myfirstapp.models.deleteUser.DeleteUserRemoteResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by taqtile on 5/11/17.
 */

public interface DeleteUserServices {

    @DELETE("user/{id}")
    Call<DeleteUserRemoteResponse> deleteUser(@Header("Authorization") String token, @Path("id") int id);

}

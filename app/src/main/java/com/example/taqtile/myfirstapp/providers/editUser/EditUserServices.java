package com.example.taqtile.myfirstapp.providers.editUser;

import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteRequest;
import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by taqtile on 5/12/17.
 */

public interface EditUserServices {

    @PUT("user/{id}")
    Call<EditUserRemoteResponse> editUser(@Header("Authorization") String token, @Path("id") int id, @Body EditUserRemoteRequest editUserRemoteRequest);

}

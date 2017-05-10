package com.example.myfirstapp.providers.createUser;

import com.example.myfirstapp.models.createUser.request.CreateUserRemoteRequest;
import com.example.myfirstapp.models.createUser.response.CreateUserRemoteResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by taqtile on 5/10/17.
 */

public interface CreateUserService {

    @POST("user")
    Call<CreateUserRemoteResponse> createUser (@Header("Authorization") String token, @Body CreateUserRemoteRequest createUserRemoteRequest);

}

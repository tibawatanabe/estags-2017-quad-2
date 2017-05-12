package com.example.taqtile.myfirstapp.providers.editUser;

import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteRequest;
import com.example.taqtile.myfirstapp.models.editUser.EditUserRemoteResponse;
import com.example.taqtile.myfirstapp.providers.RetrofitHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/12/17.
 */

public class EditUserProvider {
    private EditUserServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public void performRequest(String token, int id, EditUserRemoteRequest editUserRemoteRequest, Callback<EditUserRemoteResponse> editUserRemoteResponseCallback) {
        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(EditUserServices.class);

        Call<EditUserRemoteResponse> call = services.editUser(token, id, editUserRemoteRequest);
        call.enqueue(editUserRemoteResponseCallback);
    }
}

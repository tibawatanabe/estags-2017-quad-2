package com.example.myfirstapp.providers.listUsers;

import com.example.myfirstapp.models.listUsers.response.ListUsersRemoteResponse;
import com.example.myfirstapp.providers.RetrofitHelper;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by taqtile on 5/5/17.
 */

public class ListUsersProvider {

    private ListUsersServices services;
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public void performRequest(String token, Pagination pagination, Callback<ListUsersRemoteResponse> listUsersRemoteResponseCallback){

        retrofit = RetrofitHelper.buildRetrofit();
        services = retrofit.create(ListUsersServices.class);

        String serializedPagination = new Gson().toJson(pagination);

        //somente para teste
        Call<ListUsersRemoteResponse> call = services.listUsers(token, serializedPagination);
        call.enqueue(listUsersRemoteResponseCallback);

    }

}

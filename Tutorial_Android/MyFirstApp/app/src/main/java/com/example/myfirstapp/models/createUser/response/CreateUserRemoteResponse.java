package com.example.myfirstapp.models.createUser.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/10/17.
 */

public class CreateUserRemoteResponse {

    @SerializedName("data")
    private CreateUserDataRemoteResponse data;

    public CreateUserDataRemoteResponse getData() {
        return data;
    }

    public void setData(CreateUserDataRemoteResponse data) {
        this.data = data;
    }
}

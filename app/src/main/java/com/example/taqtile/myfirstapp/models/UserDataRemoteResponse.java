package com.example.taqtile.myfirstapp.models;

import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/10/17.
 */

public class UserDataRemoteResponse {

    @SerializedName("data")
    private UserRemoteResponse data;

    //Constructor
    public UserDataRemoteResponse(UserRemoteResponse data) {
        this.data = data;
    }

    //Getter
    public UserRemoteResponse getData() {

        return data;
    }

    //Setter
    public void setData(UserRemoteResponse data) {

        this.data = data;
    }
}

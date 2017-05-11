package com.example.taqtile.myfirstapp.models.deleteUser;

import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/11/17.
 */

public class DeleteUserRemoteResponse {

    @SerializedName("data")
    private UserRemoteResponse data;

    //Constructor
    public DeleteUserRemoteResponse(UserRemoteResponse data) {
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

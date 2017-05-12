package com.example.taqtile.myfirstapp.models.editUser;

import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/12/17.
 */

public class EditUserRemoteResponse {

    @SerializedName("data")
    private UserRemoteResponse data;

    public EditUserRemoteResponse(UserRemoteResponse data) {
        this.data = data;
    }

    public UserRemoteResponse getData() {

        return data;
    }

    public void setData(UserRemoteResponse data) {

        this.data = data;
    }
}

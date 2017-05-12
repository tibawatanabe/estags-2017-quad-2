package com.example.taqtile.myfirstapp.models.editUser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/12/17.
 */

public class EditUserRemoteRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;


    //Constructors
    public EditUserRemoteRequest() {
    }

    public EditUserRemoteRequest(String name, String email) {

        this.name = name;
        this.email = email;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    //Setters
    public void setName(String name) {

        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

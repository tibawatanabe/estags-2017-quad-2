package com.example.taqtile.myfirstapp.models.createUser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/10/17.
 */

public class CreateUserRemoteRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("type")
    private String type;


    //Constructor
    public CreateUserRemoteRequest() {
    }

    public CreateUserRemoteRequest(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }
}

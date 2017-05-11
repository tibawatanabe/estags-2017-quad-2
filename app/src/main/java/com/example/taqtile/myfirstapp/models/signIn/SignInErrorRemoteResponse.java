package com.example.taqtile.myfirstapp.models.signIn;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/5/17.
 */

public class SignInErrorRemoteResponse {

    @SerializedName("name")
    String name;

    @SerializedName("defaultMessage")
    String defaultMessage;

    @SerializedName("message")
    String message;


    public SignInErrorRemoteResponse(String name, String defaultMessage, String message) {
        this.name = name;
        this.defaultMessage = defaultMessage;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

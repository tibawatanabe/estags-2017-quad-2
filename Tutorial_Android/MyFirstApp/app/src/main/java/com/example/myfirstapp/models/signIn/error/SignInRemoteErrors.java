package com.example.myfirstapp.models.signIn.error;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/4/17.
 */

public class SignInRemoteErrors {

    @SerializedName("name")
    private String name;
    @SerializedName("defaultMessage")
    private String defaultMessage;
    @SerializedName("message")
    private String message;

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void setMessage(String message) {
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
}

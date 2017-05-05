package com.example.myfirstapp.models.signIn.error;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/4/17.
 */

public class SignInRemoteError {

    @SerializedName("errors")
    private List<SignInRemoteErrors> errors = new ArrayList<SignInRemoteErrors>();

    public List<SignInRemoteErrors> getErrors() {
        return errors;
    }

    public void setErrors(List<SignInRemoteErrors> errors) {
        this.errors = errors;
    }
}

package com.example.taqtile.myfirstapp.models.signIn;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/5/17.
 */

public class BadSignInRemoteResponse {

    @SerializedName("errors")
    List<SignInErrorRemoteResponse> signInErrorRemoteResponse = new ArrayList<SignInErrorRemoteResponse>();

    public BadSignInRemoteResponse(List<SignInErrorRemoteResponse> signInErrorRemoteResponse) {
        this.signInErrorRemoteResponse = signInErrorRemoteResponse;
    }

    public BadSignInRemoteResponse() {
        this.signInErrorRemoteResponse = signInErrorRemoteResponse;
    }

    public List<SignInErrorRemoteResponse> getSignInErrorRemoteResponse() {
        return signInErrorRemoteResponse;
    }

    public void setSignInErrorRemoteResponse(List<SignInErrorRemoteResponse> signInErrorRemoteResponse) {
        this.signInErrorRemoteResponse = signInErrorRemoteResponse;
    }
}

package com.example.myfirstapp.models.signIn.response;


import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInDataRemoteResponse {

  @SerializedName("user")
  private SignInUserRemoteResponse user;

  @SerializedName("token")
  private String token;

  public SignInDataRemoteResponse(SignInUserRemoteResponse user, String token) {
    this.user = user;
    this.token = token;
  }

  public SignInUserRemoteResponse getUser() {
    return user;
  }

  public String getToken() {
    return token;
  }

  public void setData(SignInUserRemoteResponse user) {
    this.user = user;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

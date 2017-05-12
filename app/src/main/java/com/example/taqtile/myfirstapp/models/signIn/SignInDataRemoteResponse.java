package com.example.taqtile.myfirstapp.models.signIn;

import com.example.taqtile.myfirstapp.models.UserRemoteResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInDataRemoteResponse {

  @SerializedName("user")
  private UserRemoteResponse user;

  @SerializedName("token")
  private String token;

  public SignInDataRemoteResponse(UserRemoteResponse user, String token) {
    this.user = user;
    this.token = token;
  }

  public UserRemoteResponse getUser() {
    return user;
  }

  public String getToken() {
    return token;
  }

  public void setData(UserRemoteResponse user) {
    this.user = user;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

package com.example.myfirstapp.models.signIn.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInRemoteRequest {

  @SerializedName("user")
  private String user;

  @SerializedName("password")
  private String password;

  public SignInRemoteRequest(){}

  public SignInRemoteRequest(String user, String password){
    this.user = user;
    this.password = password;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


}

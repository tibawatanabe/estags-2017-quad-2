package com.example.myfirstapp.models.signIn.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInRemoteResponse {

  @SerializedName("data")
  private SignInDataRemoteResponse data;

  public SignInRemoteResponse(SignInDataRemoteResponse data) {
    this.data = data;
  }

  public SignInDataRemoteResponse getData() {
    return data;
  }

  public void setData(SignInDataRemoteResponse data) {
    this.data = data;
  }
}

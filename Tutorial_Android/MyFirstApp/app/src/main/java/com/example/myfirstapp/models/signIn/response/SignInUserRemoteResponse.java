package com.example.myfirstapp.models.signIn.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by taqtile on 5/3/17.
 */

public class SignInUserRemoteResponse {

  @SerializedName("id")
  private int id;

  @SerializedName("name")
  private String name;

  @SerializedName("email")
  private String email;

  @SerializedName("type")
  private String type;

  @SerializedName("createdAt")
  private String createdAt;

  @SerializedName("updatedAt")
  private String updatedAt;

  public SignInUserRemoteResponse(int id, String name, String email, String type, String createdAt, String updatedAt) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.type = type;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getType() {
    return type;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}

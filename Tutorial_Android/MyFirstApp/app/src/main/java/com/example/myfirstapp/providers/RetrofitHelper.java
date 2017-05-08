package com.example.myfirstapp.providers;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taqtile on 5/3/17.
 */

public class RetrofitHelper {

  public static Retrofit buildRetrofit(){

    return new Retrofit.Builder()
      .baseUrl("https://tq-template-node.herokuapp.com/")
      .addConverterFactory(GsonConverterFactory.create(new Gson()))
      .build();
  }
}

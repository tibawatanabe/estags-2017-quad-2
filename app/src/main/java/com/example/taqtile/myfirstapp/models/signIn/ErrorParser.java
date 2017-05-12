package com.example.taqtile.myfirstapp.models.signIn;

import com.example.taqtile.myfirstapp.providers.signIn.SignInProvider;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by taqtile on 5/5/17.
 */

public class ErrorParser {


    public static BadSignInRemoteResponse parseError(Response<?> response) {
        Converter<ResponseBody, BadSignInRemoteResponse> converter =
                SignInProvider.getRetrofit()
                .responseBodyConverter(BadSignInRemoteResponse.class, new Annotation[0]);

        BadSignInRemoteResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new BadSignInRemoteResponse();
        }

        return error;
    }

}

package com.example.myfirstapp.providers.error;

import com.example.myfirstapp.models.signIn.error.SignInRemoteError;
import com.example.myfirstapp.providers.RetrofitHelper;
import com.example.myfirstapp.providers.SignInProvider;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by taqtile on 5/4/17.
 */

public class ErrorUtils {

    public static SignInRemoteError parseError(Response<?> response){

        Converter<ResponseBody, SignInRemoteError> converter = SignInProvider.getRetrofit().
                responseBodyConverter(SignInRemoteError.class, new Annotation[0]);

        SignInRemoteError error;

        try{
            error = converter.convert(response.errorBody());
        } catch (IOException e){
            return new SignInRemoteError();
        }

        return error;
    }

}

package com.example.taqtile.myfirstapp.models.signIn;

/**
 * Created by taqtile on 5/5/17.
 */

public class SignInAPIError {

    private int statusCode;
    private String message;


    public SignInAPIError() {
    }

    public SignInAPIError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

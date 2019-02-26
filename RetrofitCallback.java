package com.mandaliyamedicals.medical.retrofit;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class RetrofitCallback<T> implements Callback<T> {

    private Context context;

    public RetrofitCallback(Context c) {
        context = c;
    }

    public abstract void onSuccess(T arg0);

    public abstract void onFail(String message);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.code() == 200) {
            onSuccess(response.body());
        } else {
            onFail(ApiConstant.SOMETHING_WRONG);
        }
    }


    @Override
    public void onFailure(Call<T> call, Throwable error) {
        String errorMsg;
        error.printStackTrace();
        if (error instanceof SocketTimeoutException) {
            errorMsg = ApiConstant.TIMEOUT;
        } else if (error instanceof UnknownHostException) {
            errorMsg = ApiConstant.NO_INTERNET;
        } else if (error instanceof ConnectException) {
            errorMsg = ApiConstant.SERVER_NOT_RESPONDING;
        } else if (error instanceof JSONException || error instanceof JsonSyntaxException) {
            errorMsg = ApiConstant.PARSE_ERROR;
        } else if (error instanceof IOException) {
            errorMsg = error.getMessage();
        } else {
            errorMsg = ApiConstant.SOMETHING_WRONG;
        }

        onFail(errorMsg);
    }
}
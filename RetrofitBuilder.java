package com.mandaliyamedicals.medical.retrofit;

import android.content.Context;

import com.mandaliyamedicals.medical.generalHelper.URLs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nectabits on 31/3/16.
 */
public class RetrofitBuilder {
    public static final RetrofitBuilder retrofitBuilder = new RetrofitBuilder();

    private RetrofitBuilder() {
    }

    public static RetrofitBuilder getInstance() {
        return retrofitBuilder;
    }

    public ApiEndpointInterface getRetrofit(final Context context) {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(7000, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(logging);


        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiEndpointInterface.class);
    }

    public ApiEndpointInterface getRetrofitDoctor(final Context context) {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(7000, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(logging);


        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL_DOCTOR)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiEndpointInterface.class);
    }


    public ApiEndpointInterface getRetrofitLab(final Context context) {

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(7000, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(logging);


        final Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL_LAB)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ApiEndpointInterface.class);
    }
}



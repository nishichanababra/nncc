package app.citta.retail365cloud.retrofit;


import android.app.Activity;

import java.util.concurrent.TimeUnit;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.utils.AppPreference;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.citta.retail365cloud.utils.AppConfig.AUTH_URL;

public class RetrofitAPIClient {

    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build();

    public static Retrofit getClient(Activity activity) {

        String BASE_URL = AppPreference.getInstance().getStringPreference(activity, activity.getApplicationContext().getResources().getString(R.string.API_URL));

        retrofit = null;

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(AUTH_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
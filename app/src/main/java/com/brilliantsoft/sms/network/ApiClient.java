package com.brilliantsoft.sms.network;

import android.content.Context;
import com.brilliantsoft.sms.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient instance;
    private final Retrofit retrofit;
    private final Retrofit retrofitWithoutAuth;

    private ApiClient(Context ctx) {
        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        log.redactHeader("Authorization");
        log.redactHeader("Cookie");
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(ctx))
                .authenticator(new TokenAuthenticator(ctx))
                .addInterceptor(log)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
        OkHttpClient noAuthClient = new OkHttpClient.Builder()
                .addInterceptor(log)
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create(gson)).build();
        retrofitWithoutAuth = new Retrofit.Builder().baseUrl(Constants.BASE_URL).client(noAuthClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static synchronized ApiClient getInstance(Context ctx) {
        if (instance == null) instance = new ApiClient(ctx.getApplicationContext());
        return instance;
    }

    public ApiService getApiService() { return retrofit.create(ApiService.class); }
    public ApiService createServiceWithoutAuth() { return retrofitWithoutAuth.create(ApiService.class); }
    public Retrofit getRetrofit() { return retrofit; }
}

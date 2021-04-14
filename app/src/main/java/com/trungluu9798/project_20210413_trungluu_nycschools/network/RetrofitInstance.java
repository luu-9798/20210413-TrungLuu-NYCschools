package com.trungluu9798.project_20210413_trungluu_nycschools.network;

import com.trungluu9798.project_20210413_trungluu_nycschools.model.DirectoryData;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.ScoreData;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private RetrofitEndpoints endpoints;

    public RetrofitInstance(){
        endpoints = createRetrofitEndpoins(getInstance());
    }

    private Retrofit getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return new Retrofit
                .Builder()
                .baseUrl("https://data.cityofnewyork.us/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    private RetrofitEndpoints createRetrofitEndpoins(Retrofit retrofit) {
        return retrofit.create(RetrofitEndpoints.class);
    }

    public Call<List<DirectoryData>> getDirectoryData() {
        return endpoints.getDirectoryData();
    }

    public Call<List<ScoreData>> getScoreData() {
        return endpoints.getScoreData();
    }
}

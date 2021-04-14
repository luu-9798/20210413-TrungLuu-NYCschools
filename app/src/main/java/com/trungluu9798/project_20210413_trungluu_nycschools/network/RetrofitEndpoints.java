package com.trungluu9798.project_20210413_trungluu_nycschools.network;

import com.trungluu9798.project_20210413_trungluu_nycschools.model.DirectoryData;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.ScoreData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitEndpoints {

    @GET("/resource/s3k6-pzi2.json")
    Call<List<DirectoryData>> getDirectoryData();

    @GET("/resource/f9bf-2cp4.json")
    Call<List<ScoreData>> getScoreData();
}

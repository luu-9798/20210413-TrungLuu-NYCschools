package com.trungluu9798.project_20210413_trungluu_nycschools.ui.list;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.DirectoryData;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.ScoreData;
import com.trungluu9798.project_20210413_trungluu_nycschools.network.RetrofitInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    private RetrofitInstance retrofitInstance = new RetrofitInstance();
    private MutableLiveData<List<DirectoryData>> directoryLiveData = new MutableLiveData<>();
    private MutableLiveData<List<ScoreData>> scoreLiveData = new MutableLiveData<>();

    public MutableLiveData<List<DirectoryData>> getDirectoryLiveData() {
        return directoryLiveData;
    }
    public MutableLiveData<List<ScoreData>> getScoreLiveData() {
        return scoreLiveData;
    }

    public DirectoryData selectedSchool;
    public Boolean okToSetUpRecyclerView = false;

    public void loadData() {
        retrofitInstance.getDirectoryData().enqueue(new Callback<List<DirectoryData>>() {
            @Override
            public void onResponse(Call<List<DirectoryData>> call, Response<List<DirectoryData>> response) {
                Log.d("TAG_X", "pass");
                directoryLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DirectoryData>> call, Throwable t) {
                Log.d("TAB_X", "Fail");
            }
        });

        retrofitInstance.getScoreData().enqueue(new Callback<List<ScoreData>>() {
            @Override
            public void onResponse(Call<List<ScoreData>> call, Response<List<ScoreData>> response) {
                Log.d("TAG_X", "pass");
                scoreLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ScoreData>> call, Throwable t) {
                Log.d("TAB_X", "Fail");
            }
        });
    }
}

package com.trungluu9798.project_20210413_trungluu_nycschools.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.trungluu9798.project_20210413_trungluu_nycschools.R;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.DirectoryData;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.ScoreData;
import com.trungluu9798.project_20210413_trungluu_nycschools.ui.list.ListViewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DetailFragment extends Fragment {

    private TextView schoolName;
    private TextView testTakers;
    private TextView avgReading;
    private TextView avgWriting;
    private TextView avgMath;

    private ListViewModel viewModel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
    }

    public void onStart() {
        super.onStart();

        List<ScoreData> list = viewModel.getScoreLiveData().getValue();
        ScoreData selected = null;

        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getDbn().equals(viewModel.selectedSchool.getDbn())) {
                selected = list.get(i);
                break;
            }
        }

        if (selected == null) {
            setData(
                viewModel.selectedSchool.getSchoolName().toUpperCase(),
                "N/A",
                "N/A",
                "N/A",
                "N/A"
            );
        } else {
            setData(
                selected.getSchoolName(),
                selected.getNumOfSatTestTakers(),
                selected.getSatCriticalReadingAvgScore(),
                selected.getSatMathAvgScore(),
                selected.getSatWritingAvgScore()
            );
        }

    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        schoolName = (TextView) rootView.findViewById(R.id.school_name);
        testTakers = (TextView) rootView.findViewById(R.id.num_of_sat_test_takers);
        avgReading = (TextView) rootView.findViewById(R.id.sat_critical_reading_avg_score);
        avgMath = (TextView) rootView.findViewById(R.id.sat_math_avg_score);
        avgWriting = (TextView) rootView.findViewById(R.id.sat_writing_avg_score);

        return rootView;
    }

    private void setData(
            String schoolNameString,
            String testTakersString,
            String avgReadingString,
            String avgMathString,
            String avgWritingString) {
        schoolName.setText(schoolNameString);
        testTakers.setText(getResources().getString(R.string.num_of_sat_test_takers, testTakersString));
        avgReading.setText(getResources().getString(R.string.sat_critical_reading_avg_score, avgReadingString));
        avgWriting.setText(getResources().getString(R.string.sat_writing_avg_score, avgWritingString));
        avgMath.setText(getResources().getString(R.string.sat_math_avg_score, avgMathString));
    }

}

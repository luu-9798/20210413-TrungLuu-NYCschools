package com.trungluu9798.project_20210413_trungluu_nycschools.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.trungluu9798.project_20210413_trungluu_nycschools.R;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.DirectoryData;
import com.trungluu9798.project_20210413_trungluu_nycschools.ui.detail.DetailFragment;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class ListFragment extends Fragment implements ListAdapter.ListAdapterDelegate {
    private ListViewModel viewModel;
    private RecyclerView recyclerView;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setUpViewModel();
        this.setUpObserver();
        viewModel.loadData();
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_view);

        return rootView;
    }

    public void onResume() {
        super.onResume();
        if (viewModel.okToSetUpRecyclerView) {
            setUpRecyclerView();
        }
    }

    private void setUpViewModel() {
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
    }

    private void setUpObserver() {
        Observer directoryObserver = (Observer<List<DirectoryData>>) list -> {
            viewModel.okToSetUpRecyclerView = true;
            setUpRecyclerView();
        };

        viewModel.getDirectoryLiveData().observe(this, directoryObserver);
    }

    private void setUpRecyclerView() {
        List<DirectoryData> response = viewModel.getDirectoryLiveData().getValue();
        Comparator<DirectoryData> compareSchoolName = (DirectoryData s1, DirectoryData s2)
                -> s1.getSchoolName().compareTo(s2.getSchoolName());
        Collections.sort(response, compareSchoolName);
        ListAdapter adapter = new ListAdapter(response, this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(), RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void listSelected(DirectoryData selected) {
        viewModel.selectedSchool = selected;
        DetailFragment detailFragment = new DetailFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, detailFragment)
                .addToBackStack("detail")
                .commit();
    }
}

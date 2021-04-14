package com.trungluu9798.project_20210413_trungluu_nycschools.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trungluu9798.project_20210413_trungluu_nycschools.R;
import com.trungluu9798.project_20210413_trungluu_nycschools.model.DirectoryData;

import java.util.List;

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<DirectoryData> list;
    private ListAdapterDelegate delegate;

    public ListAdapter(List<DirectoryData> list,  ListAdapterDelegate delegate) {
        this.list = list;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        String schoolName = list.get(position).getSchoolName();
        holder.textView
                .setText(schoolName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.listSelected(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
        }
    }

    public interface ListAdapterDelegate {
        void listSelected(DirectoryData selected);
    }
}

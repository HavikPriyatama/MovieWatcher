package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.model.Crew;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewAdapterVH> {

    Context context;
    List<Crew> crewList;

    public CrewAdapter(Context context, List<Crew> crewList) {
        this.context = context;
        this.crewList = crewList;
    }

    @NonNull
    @Override
    public CrewAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crew, parent,false);
        return new CrewAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapterVH holder, int i) {
        holder.crewName.setText(crewList.get(i).getName());
        holder.crewJob.setText(crewList.get(i).getJob());
    }

    @Override
    public int getItemCount() {
        return crewList.size() > 10 ? 9 : crewList.size();
    }

    class CrewAdapterVH extends RecyclerView.ViewHolder{
        @BindView(R.id.realName_crew)
        TextView crewName;
        @BindView(R.id.job_crew)
        TextView crewJob;
        public CrewAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

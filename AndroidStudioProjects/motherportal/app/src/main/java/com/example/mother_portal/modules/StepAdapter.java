package com.example.mother_portal.modules;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mother_portal.R;
import com.example.mother_portal.modules.VideoAndDoc;

import java.util.List;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private final List<StepItem> stepItems;

    public StepAdapter(List<StepItem> stepItems) {
        this.stepItems = stepItems;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_step, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        StepItem step = stepItems.get(position);
        holder.stepTitle.setText(step.getTitle());

        // Set adapter for sub-items
        SubAdapter subAdapter = new SubAdapter(step.getSubItems());
        holder.subRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.subRecyclerView.setAdapter(subAdapter);

        // Expand / Collapse logic
        holder.stepHeader.setOnClickListener(v -> {
            if (step.isExpanded()) {
                holder.subRecyclerView.setVisibility(View.GONE);
                holder.stepArrow.setRotation(0);
                step.setExpanded(false);
            } else {
                holder.subRecyclerView.setVisibility(View.VISIBLE);
                holder.stepArrow.setRotation(180);
                step.setExpanded(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepItems.size();
    }

    static class StepViewHolder extends RecyclerView.ViewHolder {
        TextView stepTitle;
        ImageView stepArrow;
        LinearLayout stepHeader;
        RecyclerView subRecyclerView;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.stepTitle);
            stepArrow = itemView.findViewById(R.id.stepArrow);
            stepHeader = itemView.findViewById(R.id.stepHeader);
            subRecyclerView = itemView.findViewById(R.id.stepSubRecyclerView);
        }
    }
}

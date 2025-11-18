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

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.SubViewHolder> {

    private final List<SubItem> subItems;

    public SubAdapter(List<SubItem> subItems) {
        this.subItems = subItems;
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sub, parent, false);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder holder, int position) {
        SubItem subItem = subItems.get(position);
        holder.subItemTitle.setText(subItem.getTitle());

        // Always show arrow
        holder.subItemArrow.setVisibility(View.VISIBLE);

        // Handle click
        holder.subItemHeader.setOnClickListener(v -> {
            if (subItem.getSubItems() != null && !subItem.getSubItems().isEmpty()) {
                // Expand/collapse nested sub-items
                if (subItem.isExpanded()) {
                    holder.subItemRecyclerView.setVisibility(View.GONE);
                    subItem.setExpanded(false);
                    holder.subItemArrow.setRotation(0);
                } else {
                    holder.subItemRecyclerView.setVisibility(View.VISIBLE);
                    holder.subItemRecyclerView.setLayoutManager(
                            new LinearLayoutManager(holder.itemView.getContext()));
                    holder.subItemRecyclerView.setAdapter(
                            new SubAdapter(subItem.getSubItems()));
                    subItem.setExpanded(true);
                    holder.subItemArrow.setRotation(180);
                }
            } else {
                // Leaf item → open VideoAndDocActivity
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context,com.example.mother_portal.modules.VideoAndDoc.class);
                intent.putExtra("title", subItem.getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subItems.size();
    }

    static class SubViewHolder extends RecyclerView.ViewHolder {

        TextView subItemTitle;
        ImageView subItemArrow;
        RecyclerView subItemRecyclerView;
        LinearLayout subItemHeader;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            subItemTitle = itemView.findViewById(R.id.subItemTitle);
            subItemArrow = itemView.findViewById(R.id.subItemArrow);
            subItemRecyclerView = itemView.findViewById(R.id.subItemRecyclerView);
            subItemHeader = itemView.findViewById(R.id.subItemHeader);
        }
    }
}

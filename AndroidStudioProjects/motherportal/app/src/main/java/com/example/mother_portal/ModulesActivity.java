package com.example.mother_portal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mother_portal.modules.StepAdapter;
import com.example.mother_portal.modules.StepItem;
import com.example.mother_portal.modules.SubItem;

import java.util.ArrayList;
import java.util.List;

public class ModulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

        ImageView menuicon = findViewById(R.id.menuIcon);
        menuicon.setOnClickListener(v -> {
            androidx.appcompat.widget.PopupMenu popup = new androidx.appcompat.widget.PopupMenu(ModulesActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.header_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.menu_about) startActivity(new Intent(this, AboutActivity.class));
                else if (id == R.id.menu_join) startActivity(new Intent(this, JoinCommunity.class));
                else if (id == R.id.menu_community) startActivity(new Intent(this, CommunityActivity.class));
                else if (id == R.id.menu_modules) startActivity(new Intent(this, ModulesActivity.class));
                else if (id == R.id.menu_home) startActivity(new Intent(this, MainActivity.class));
                return true;
            });
            popup.show();
        });

        // ------------------- STEPS RECYCLER VIEW -------------------
        RecyclerView stepsRecyclerView = findViewById(R.id.stepsRecyclerView);

        // --- Step 1 sub-items ---
        List<SubItem> splunkSubSubItems = new ArrayList<>();
        splunkSubSubItems.add(new SubItem("Indexers Overview"));
        splunkSubSubItems.add(new SubItem("Search Heads Overview"));
        splunkSubSubItems.add(new SubItem("Forwarders Overview"));

        List<SubItem> installSplunkSubItems = new ArrayList<>();
        installSplunkSubItems.add(new SubItem("Download Splunk Package"));
        installSplunkSubItems.add(new SubItem("Install Splunk on EC2"));
        installSplunkSubItems.add(new SubItem("Start Splunk Service"));

        List<SubItem> step1SubItems = new ArrayList<>();
        step1SubItems.add(new SubItem("How to create EC2 Instance?"));
        step1SubItems.add(new SubItem("How to install Splunk in EC2?", installSplunkSubItems));
        step1SubItems.add(new SubItem("Splunk Architecture Basics", splunkSubSubItems));
        step1SubItems.add(new SubItem("Connecting Splunk Components"));
        step1SubItems.add(new SubItem("Splunk Universal Forwarder Installation"));

        // --- Step 2 sub-items ---
        List<SubItem> step2SubItems = new ArrayList<>();
        step2SubItems.add(new SubItem("Configure Inputs"));
        step2SubItems.add(new SubItem("Configure Indexes"));
        step2SubItems.add(new SubItem("Configure Forwarders"));

        // --- Steps list ---
        List<StepItem> steps = new ArrayList<>();
        steps.add(new StepItem("Step 1 - Get started with Splunk", step1SubItems));
        steps.add(new StepItem("Step 2 - Configure Splunk", step2SubItems));

        // --- Set StepAdapter ---
        StepAdapter stepAdapter = new StepAdapter(steps);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stepsRecyclerView.setAdapter(stepAdapter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                view instanceof android.widget.EditText &&
                !view.getClass().getName().startsWith("android.webkit")) {

            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];

            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}

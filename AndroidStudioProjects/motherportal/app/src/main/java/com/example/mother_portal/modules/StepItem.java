package com.example.mother_portal.modules;

import java.util.List;

public class StepItem {
    private String title;
    private List<SubItem> subItems;
    private boolean isExpanded = false;

    public StepItem(String title, List<SubItem> subItems) {
        this.title = title;
        this.subItems = subItems;
    }

    public String getTitle() {
        return title;
    }

    public List<SubItem> getSubItems() {
        return subItems;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}

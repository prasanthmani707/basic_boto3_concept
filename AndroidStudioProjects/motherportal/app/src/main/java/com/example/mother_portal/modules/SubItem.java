package com.example.mother_portal.modules;

import java.util.List;

public class SubItem {
    private String title;
    private List<SubItem> subItems; // nested items
    private boolean isExpanded = false;

    public SubItem(String title, List<SubItem> subItems) {
        this.title = title;
        this.subItems = subItems;
    }

    public SubItem(String title) {
        this.title = title;
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

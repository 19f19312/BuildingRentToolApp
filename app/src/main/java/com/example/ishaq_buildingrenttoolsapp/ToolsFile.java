package com.example.ishaq_buildingrenttoolsapp;

import com.google.firebase.database.Exclude;

public class ToolsFile {
    public String toolName;
    public String toolPrice;
    public String toolDetail;
    public String toolUrl;

    public String mKey;

    public ToolsFile() {
    }

    public ToolsFile(String toolName, String toolPrice, String toolDetail, String toolUrl) {
        this.toolName = toolName;
        this.toolPrice = toolPrice;
        this.toolDetail = toolDetail;
        this.toolUrl = toolUrl;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolPrice() {
        return toolPrice;
    }

    public void setToolPrice(String toolPrice) {
        this.toolPrice = toolPrice;
    }

    public String getToolDetail() {
        return toolDetail;
    }

    public void setToolDetail(String toolDetail) {
        this.toolDetail = toolDetail;
    }

    public String getToolUrl() {
        return toolUrl;
    }

    public void setToolUrl(String toolUrl) {
        this.toolUrl = toolUrl;
    }


    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}

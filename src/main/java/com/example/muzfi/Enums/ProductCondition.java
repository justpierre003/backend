package com.example.muzfi.Enums;

public enum ProductCondition {
    NEW("new"), USED("used"), OPENED("opened"), DUPE("dupe");

    private final String displayName;

    ProductCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
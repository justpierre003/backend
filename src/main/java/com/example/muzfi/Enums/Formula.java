package com.example.muzfi.Enums;

public enum Formula {
    EF("Eau Fraiche"), EDC("Eau De Cologne"), EDT("Eau De Toilette"), EDP("Eau De Parfume"), PAR("Parfume"), BOD("Body Spray"), OIL("Oil");

    private final String displayName;

    Formula(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
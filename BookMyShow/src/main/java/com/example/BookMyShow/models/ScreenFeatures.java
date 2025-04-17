package com.example.BookMyShow.models;

public enum ScreenFeatures {
    DOLBY_ATMOS("Dolby Atmos"),
    IMAX("IMAX"),
    THREE_D("3D"),
    FOUR_D("4D"),
    D_BOX("D-Box"),
    SCREEN_X("Screen X");

    private final String feature;

    ScreenFeatures(String feature) {
        this.feature = feature;
    }

    public String getFeature() {
        return feature;
    }
}

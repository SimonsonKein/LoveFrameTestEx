package com.simonsonkein.loveframestestex.view_models;

import android.view.View;

public class FilterModel {
    private final String name;
    private final int imageResId;
    private final int startColor;
    private final int endColor;
    private View.OnClickListener onClickListener;

    public FilterModel(String text, int imageResId, int startColor, int endColor) {
        this.name = text;
        this.imageResId = imageResId;
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public FilterModel(String text, int imageResId, View.OnClickListener onClickListener, int startColor, int endColor) {
        this.name = text;
        this.imageResId = imageResId;
        this.onClickListener = onClickListener;
        this.startColor = startColor;
        this.endColor = endColor;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getStartColor() {
        return startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}

package com.simonsonkein.loveframestestex.view_models;

import android.content.Context;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.simonsonkein.loveframestestex.R;

import java.util.ArrayList;
import java.util.List;

public class FrameModel {
    public static final int TYPE_SINGLE = 1;
    public static final int TYPE_WIDE = 2;
    public static final int TYPE_TALL = 3;

    private final int type;
    private final int imageResId;

    public FrameModel(int type, int imageResId) {
        this.type = type;
        this.imageResId = imageResId;
    }

    public static List<FrameModel> getFrameList() {
        List<FrameModel> frameModelList = new ArrayList<>();
        frameModelList.add(new FrameModel(FrameModel.TYPE_TALL, R.drawable.large));
        frameModelList.add(new FrameModel(FrameModel.TYPE_SINGLE, R.drawable.small_1));
        frameModelList.add(new FrameModel(FrameModel.TYPE_TALL, R.drawable.large_1));
        frameModelList.add(new FrameModel(FrameModel.TYPE_SINGLE, R.drawable.small_2));
        frameModelList.add(new FrameModel(FrameModel.TYPE_TALL, R.drawable.large_5));
        frameModelList.add(new FrameModel(FrameModel.TYPE_SINGLE, R.drawable.small_5));
        frameModelList.add(new FrameModel(FrameModel.TYPE_TALL, R.drawable.large_2));
        frameModelList.add(new FrameModel(FrameModel.TYPE_SINGLE, R.drawable.small_4));
        frameModelList.add(new FrameModel(FrameModel.TYPE_WIDE, R.drawable.wide_1));
        return frameModelList;
    }

    public static void updateGridLayout(Context context, GridLayout layout, List<FrameModel> frameModelList, int columnCount) {
        layout.removeAllViews();
        layout.setColumnCount(columnCount);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.setGravity(Gravity.CENTER);
        params.width = GridLayout.LayoutParams.MATCH_PARENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        for (int i = 0; i < frameModelList.size(); i++) {
            params.columnSpec = GridLayout.spec(i % columnCount, 1f);
            params.rowSpec = GridLayout.spec(i / columnCount);
            params.setGravity(Gravity.CENTER_HORIZONTAL);
            FrameModel model = frameModelList.get(i);
            switch (model.getType()) {
                case FrameModel.TYPE_TALL:
                    addItemToGridLayout(context, layout, model.getImageResId(), 1, 2);
                    break;
                case FrameModel.TYPE_SINGLE:
                    addItemToGridLayout(context, layout, model.getImageResId(), 1, 1);
                    break;
                case FrameModel.TYPE_WIDE:
                    addItemToGridLayout(context, layout, model.getImageResId(), 2, 1);
                    break;
            }
        }

        layout.setLayoutParams(params);
    }

    private static void addItemToGridLayout(Context context, GridLayout gridLayout, int imageResource, int colSpan, int rowSpan) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageResource);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, colSpan, 1f); // Column span and weight
        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, rowSpan); // Row span
        params.setMargins(10, 10, 10, 10);
        params.setGravity(Gravity.CENTER_HORIZONTAL);
        imageView.setLayoutParams(params);
        gridLayout.addView(imageView);
    }

    public int getType() {
        return type;
    }

    public int getImageResId() {
        return imageResId;
    }
}

package com.simonsonkein.loveframestestex;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simonsonkein.loveframestestex.adapters.FilterAdapter;
import com.simonsonkein.loveframestestex.helpers.GridSpacingItemDecoration;
import com.simonsonkein.loveframestestex.helpers.Utils;
import com.simonsonkein.loveframestestex.view_models.FilterModel;
import com.simonsonkein.loveframestestex.view_models.FrameModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView filtersRv;
    boolean listSorted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.status_bar_color)); // Replace with your color
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        filtersInit();
        framesInit();
    }

    private void filtersInit() {
        filtersRv = findViewById(R.id.filters_rv);

        int spanCount = 3;
        int spacing = Utils.dpToPx(getApplicationContext(), 16);
        boolean includeEdge = false;
        filtersRv.setLayoutManager(new GridLayoutManager(this, spanCount));
        filtersRv.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        List<FilterModel> filterModels = new ArrayList<>();
        filterModels.add(new FilterModel("Single Frames", R.drawable.ic_frame_single, view -> {
            GridLayout frameGL = view.getRootView().findViewById(R.id.frames_gl);
            List<FrameModel> frameModelList = FrameModel.getFrameList();
            if (!listSorted) frameModelList.removeIf(c -> c.getType() != FrameModel.TYPE_SINGLE);
            FrameModel.updateGridLayout(this, frameGL, frameModelList, 2);
            listSorted = !listSorted;
        }, Color.parseColor("#FFC0C0"), Color.parseColor("#FF8181")));
        filterModels.add(new FilterModel("Double Frames", R.drawable.ic_frame_double, Color.parseColor("#FFC0C0"), Color.parseColor("#FF8181")));
        filterModels.add(new FilterModel("Triple Frames", R.drawable.ic_frame_triple, Color.parseColor("#FFC0C0"), Color.parseColor("#FF8181")));
        filterModels.add(new FilterModel("Collage", R.drawable.ic_collage, Color.parseColor("#FECE96"), Color.parseColor("#FFAC4B")));
        filterModels.add(new FilterModel("Photo Edit", R.drawable.ic_edit, Color.parseColor("#C0D9FF"), Color.parseColor("#D781FF")));
        filterModels.add(new FilterModel("Add text", R.drawable.ic_text, Color.parseColor("#BDF1FF"), Color.parseColor("#3BCFF8")));

        FilterAdapter filterAdapter = new FilterAdapter(this, filterModels, getResources().getDisplayMetrics());
        filtersRv.setAdapter(filterAdapter);

    }

    private void framesInit() {
        List<FrameModel> frameModelList = FrameModel.getFrameList();
        FrameModel.updateGridLayout(this, findViewById(R.id.frames_gl), frameModelList, 2);
    }
}

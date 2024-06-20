package com.simonsonkein.loveframestestex.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simonsonkein.loveframestestex.R;
import com.simonsonkein.loveframestestex.view_models.FilterModel;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {
    private final List<FilterModel> filterModelList;
    private final LayoutInflater inflater;
    private final DisplayMetrics displayMetrics;

    public FilterAdapter(Context context, List<FilterModel> itemList, DisplayMetrics displayMetrics) {
        this.filterModelList = itemList;
        this.inflater = LayoutInflater.from(context);
        this.displayMetrics = displayMetrics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.filter_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilterModel item = filterModelList.get(position);
        holder.itemText.setText(item.getName());
        holder.itemView.setOnClickListener(item.getOnClickListener());


        holder.itemImage.setBackground(setLinearGradient(item.getStartColor(), item.getEndColor()));
        holder.itemImage.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return filterModelList.size();
    }

    private GradientDrawable setLinearGradient(int startColor, int endColor) {
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.BR_TL,
                new int[]{startColor, endColor}
        );

        float cornerRadius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
        gradientDrawable.setCornerRadius(cornerRadius);
        return gradientDrawable;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.filter_item_icon_iv);
            itemText = itemView.findViewById(R.id.filter_item_tv);
        }
    }
}

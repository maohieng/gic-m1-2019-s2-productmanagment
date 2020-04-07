package edu.itc.m1.databinding.recycler;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AmountGridItemDecor extends RecyclerView.ItemDecoration {

    private final int horizontal, vertical, spanCount;

    public AmountGridItemDecor(Context context, int spanCount) {
        vertical = UiUtils.pixelFromDp(context, 8);
        horizontal = UiUtils.pixelFromDp(context, 8);
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        RecyclerView.ViewHolder childViewHolder = parent.getChildViewHolder(view);
        int adapterPosition = childViewHolder.getAdapterPosition();

        // Vertical
        outRect.bottom = vertical;
        if (adapterPosition < spanCount) {
            outRect.top = 0;
        } else {
            outRect.top = vertical;
        }

        // Horizontal
        outRect.left = horizontal;
        outRect.right = horizontal;
//            if (adapterPosition % spanCount == 0) {
//                outRect.right = horizontal;
//                outRect.left = 0;
//            } else {
//                outRect.right = 0;
//                outRect.left = horizontal;
//            }
    }
}
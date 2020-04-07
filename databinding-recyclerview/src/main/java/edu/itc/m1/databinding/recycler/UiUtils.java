package edu.itc.m1.databinding.recycler;

import android.content.Context;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 3/20/2020
 */
public final class UiUtils {

    private UiUtils() {
        //no instance
    }

    public static int pixelFromDp(Context context, int dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) Math.ceil(dpValue * density);
    }
}

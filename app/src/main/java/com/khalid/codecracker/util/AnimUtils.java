package com.khalid.codecracker.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.codecracker.R;

public class AnimUtils {

    public static void rotate(View v) {
        Animation rotate = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate);
        v.startAnimation(rotate);
    }

    public static void reverseRotate(View v) {
        Animation rotate = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotate_reverse);
        v.startAnimation(rotate);
    }


}

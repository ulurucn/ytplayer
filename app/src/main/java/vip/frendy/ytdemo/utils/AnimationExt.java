package vip.frendy.ytdemo.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by frendy on 2017/12/3.
 */

public class AnimationExt {

    public static void doAnimation(View view, int id) {
        Animation anim = AnimationUtils.loadAnimation(view.getContext(), id);
        view.startAnimation(anim);
    }
}

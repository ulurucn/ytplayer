package vip.frendy.ytdemo.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by frendy on 2017/12/3.
 */

public class VibratorExt {

    public static void playShort(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }

    public static void playLong(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }

    public static void playMovement(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 100, 100, 100, 100};
        vibrator.vibrate(pattern, -1);
    }
}

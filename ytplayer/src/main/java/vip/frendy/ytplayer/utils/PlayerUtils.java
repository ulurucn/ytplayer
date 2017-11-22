package vip.frendy.ytplayer.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import vip.frendy.ytplayer.R;

/**
 * Created by frendy on 2017/11/22.
 */

public class PlayerUtils {

    public static String getPlayerHTML(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.player);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String read;
            StringBuilder sb = new StringBuilder("");

            while ((read = bufferedReader.readLine()) != null)
                sb.append(read).append("\n");

            inputStream.close();

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

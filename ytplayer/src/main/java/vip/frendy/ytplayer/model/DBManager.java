package vip.frendy.ytplayer.model;

import android.content.Context;

import com.litesuits.orm.LiteOrm;

/**
 * Created by frendy on 2017/11/27.
 */

public class DBManager {

    private static String DB_NAME = "yt_music.db";
    private static LiteOrm mManager;

    public static LiteOrm getInstance(Context context) {
        if(mManager == null) {
            mManager = LiteOrm.newSingleInstance(
                    context.getApplicationContext(), DB_NAME);
        }
        return mManager;
    }

}

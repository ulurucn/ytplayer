package vip.frendy.ytplayer.extension;

import java.util.Random;

import vip.frendy.ytplayer.model.PlaylistItems;

/**
 * Created by frendy on 2017/11/20.
 */

public class PraseHelper<T> {

    public String getTitle(T item) {
        if(item instanceof String) {
            return (String) item;
        } else if(item instanceof PlaylistItems) {
            return ((PlaylistItems) item).getSnippet().getTitle();
        } else {
            return "";
        }
    }

    public String getVideoId(T item) {
        if(item instanceof String) {
            return (String) item;
        } else if(item instanceof PlaylistItems) {
            return ((PlaylistItems) item).getSnippet().getResourceId().getVideoId();
        } else {
            return "";
        }
    }

    public int getRandomIndex(int index, int bound) {
        boolean isDiff = false;
        Random random = new Random();
        int ret = 0;

        while (!isDiff) {
            ret = random.nextInt(bound);

            if(ret != index) isDiff = true;
        }
        return ret;
    }
}

package vip.frendy.ytplayer.extension;

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
}

package vip.frendy.ytdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import vip.frendy.ytdemo.R;

/**
 * Created by frendy on 2017/11/15.
 */

public class ImageViewExt {

    public static void loadImage(Context context, ImageView view, String url) {
        Glide.with(context).load(url).centerCrop().into(view);
    }

    public static void loadImageBlur(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .bitmapTransform(new BlurTransformation(context, 23, 4)) // “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
                .into(view);
    }


    public static void loadImageBlurAndFilter(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .bitmapTransform(
                        new BlurTransformation(context, 23, 4),
                        new ColorFilterTransformation(context, R.color.black))
                .into(view);
    }

    public static void loadImageCircle(final Context context, ImageView view, String url) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static int DEFAULT_RADIUS = 16;
    public static void loadImageRoundedCorners(Context context, ImageView view, String url,
                                               int radius, int margin, RoundedCornersTransformation.CornerType cornerType) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .bitmapTransform(new RoundedCornersTransformation(context, radius, margin, cornerType))
                .into(view);
    }


    public static Bitmap getBitmap(Context context, String url, int w, int h) {
        try {
            return Glide.with(context).load(url).asBitmap().centerCrop().into(w, h).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

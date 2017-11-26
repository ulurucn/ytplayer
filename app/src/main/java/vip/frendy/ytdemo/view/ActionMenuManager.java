package vip.frendy.ytdemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import vip.frendy.ytdemo.utils.Utils;

public class ActionMenuManager extends RecyclerView.OnScrollListener implements View.OnAttachStateChangeListener {

    private static ActionMenuManager instance;

    private ActionMenu mMenuView;

    private boolean isMenuDismissing;
    private boolean isMenuShowing;

    public static ActionMenuManager getInstance() {
        if (instance == null) {
            instance = new ActionMenuManager();
        }
        return instance;
    }

    private ActionMenuManager() {

    }

    public void toggleActionMenuFromView(View openingView, int item, ActionMenu.OnMenuItemClickListener listener) {
        if (mMenuView == null) {
            showActionMenuFromView(openingView, item, listener);
        } else {
            hideActionMenu();
        }
    }

    private void showActionMenuFromView(final View openingView, int item, ActionMenu.OnMenuItemClickListener listener) {
        if (!isMenuShowing) {
            isMenuShowing = true;
            mMenuView = new ActionMenu(openingView.getContext());
            mMenuView.bindToItem(item);
            mMenuView.addOnAttachStateChangeListener(this);
            mMenuView.setOnMenuItemClickListener(listener);

            ((ViewGroup) openingView.getRootView().findViewById(android.R.id.content)).addView(mMenuView);

            mMenuView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mMenuView.getViewTreeObserver().removeOnPreDrawListener(this);
                    setupActionMenuInitialPosition(openingView);
                    performShowAnimation();
                    return false;
                }
            });
        }
    }

    private void setupActionMenuInitialPosition(View openingView) {
        final int[] openingViewLocation = new int[2];
        openingView.getLocationOnScreen(openingViewLocation);
        int additionalBottomMargin = Utils.dpToPx(-8);
        mMenuView.setTranslationX(openingViewLocation[0] - mMenuView.getWidth() / 2);
        mMenuView.setTranslationY(openingViewLocation[1] - mMenuView.getHeight() - additionalBottomMargin);
    }

    private void performShowAnimation() {
        mMenuView.setPivotX(mMenuView.getWidth());
        mMenuView.setPivotY(mMenuView.getHeight());
        mMenuView.setScaleX(0.1f);
        mMenuView.setScaleY(0.1f);
        mMenuView.animate()
                .scaleX(1f).scaleY(1f)
                .setDuration(150)
                .setInterpolator(new OvershootInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isMenuShowing = false;
                    }
                });
    }

    public void hideActionMenu() {
        if (!isMenuDismissing) {
            isMenuDismissing = true;
            performDismissAnimation();
        }
    }

    private void performDismissAnimation() {
        mMenuView.setPivotX(mMenuView.getWidth());
        mMenuView.setPivotY(mMenuView.getHeight());
        mMenuView.animate()
                .scaleX(0.1f).scaleY(0.1f)
                .setDuration(150)
                .setInterpolator(new AccelerateInterpolator())
                .setStartDelay(100)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (mMenuView != null) {
                            mMenuView.dismiss();
                        }
                        isMenuDismissing = false;
                    }
                });
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (mMenuView != null) {
            hideActionMenu();
            mMenuView.setTranslationY(mMenuView.getTranslationY() - dy);
        }
    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        mMenuView = null;
    }
}

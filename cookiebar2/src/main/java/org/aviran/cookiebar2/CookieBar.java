package org.aviran.cookiebar2;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/**
 * CookieBar is a lightweight library for showing a brief message at the top or bottom of the
 * screen. <p>
 * <pre>
 * new CookieBar
 *      .Builder(MainActivity.this)
 *      .setTitle("TITLE")
 *      .setMessage("MESSAGE")
 *      .setAction("ACTION", new OnActionClickListener() {})
 *      .show();
 * </pre>
 * <p> Created by Eric on 2017/3/2.
 */
public class CookieBar {
    public static Builder Build(Activity activity) {
        return new CookieBar.Builder(activity);
    }
    private Cookie cookieView;
    private Activity context;

    private CookieBar(Activity context, Params params) {
        this.context = context;
        cookieView = new Cookie(context);
        cookieView.setParams(params);
    }

    public void show() {
        if (cookieView != null) {
            final ViewGroup decorView = (ViewGroup) context.getWindow().getDecorView();
            final ViewGroup content = (ViewGroup) decorView.findViewById(android.R.id.content);
            if (cookieView.getParent() == null) {
                ViewGroup parent = cookieView.getLayoutGravity() == Gravity.BOTTOM ?
                        content : decorView;
                addCookie(parent, cookieView);
            }
        }
    }

    private void addCookie(final ViewGroup parent, final Cookie cookie) {
        if(cookie.getParent() != null) {
            return;
        }

        // if exists, remove existing cookie
        int childCount = parent .getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if(child instanceof Cookie) {
                ((Cookie) child).dismiss(new Cookie.CookieBarDismissListener() {
                    @Override
                    public void onDismiss() {
                        parent.addView(cookie);
                    }
                });
                return;
            }
        }

        parent.addView(cookie);
    }

    public static class Builder {

        private Params params = new Params();

        public Activity context;

        /**
         * Create a builder for an cookie.
         */
        public Builder(Activity activity) {
            this.context = activity;
        }

        public Builder setIcon(@DrawableRes int iconResId) {
            params.iconResId = iconResId;
            return this;
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int resId) {
            params.title = context.getString(resId);
            return this;
        }

        public Builder setMessage(String message) {
            params.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int resId) {
            params.message = context.getString(resId);
            return this;
        }

        public Builder setDuration(long duration) {
            params.duration = duration;
            return this;
        }

        public Builder setTitleColor(@ColorRes int titleColor) {
            params.titleColor = titleColor;
            return this;
        }

        public Builder setMessageColor(@ColorRes int messageColor) {
            params.messageColor = messageColor;
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColor) {
            params.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setActionColor(@ColorRes int actionColor) {
            params.actionColor = actionColor;
            return this;
        }

        public Builder setAction(String action, OnActionClickListener onActionClickListener) {
            params.action = action;
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setIconAnimation(@AnimatorRes int iconAnimation) {
            params.iconAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, iconAnimation);
            return this;
        }

        public Builder setAction(@StringRes int resId, OnActionClickListener onActionClickListener) {
            params.action = context.getString(resId);
            params.onActionClickListener = onActionClickListener;
            return this;
        }

        public Builder setLayoutGravity(int layoutGravity) {
            params.layoutGravity = layoutGravity;
            return this;
        }

        public CookieBar create() {
            CookieBar cookie = new CookieBar(context, params);
            return cookie;
        }

        public CookieBar show() {
            final CookieBar cookie = create();
            cookie.show();
            return cookie;
        }
    }

    final static class Params {
        public String title;
        public String message;
        public String action;
        public OnActionClickListener onActionClickListener;
        public int iconResId;
        public int backgroundColor;
        public int titleColor;
        public int messageColor;
        public int actionColor;
        public long duration = 2000;
        public int layoutGravity = Gravity.TOP;
        public AnimatorSet iconAnimator;
    }
}
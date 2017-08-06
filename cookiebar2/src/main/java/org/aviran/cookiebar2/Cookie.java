package org.aviran.cookiebar2;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Eric on 2017/3/2.
 */
final class Cookie extends LinearLayout implements View.OnTouchListener {

    private long slideOutAnimationDuration = 500;
    private Animation slideInAnimation;
    private Animation slideOutAnimation;

    private LinearLayout layoutCookie;
    private TextView tvTitle;
    private TextView tvMessage;
    private ImageView ivIcon;
    private TextView btnAction;
    private long duration = 2000;
    private int layoutGravity = Gravity.BOTTOM;
    private float initialDragX;
    private float dismissOffsetThreshold;
    private float viewWidth;
    private boolean swipedOut;


    public Cookie(@NonNull final Context context) {
        this(context, null);
    }

    public Cookie(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Cookie(@NonNull final Context context, @Nullable final AttributeSet attrs,
                  final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public int getLayoutGravity() {
        return layoutGravity;
    }

    private void initViews(Context context) {
        inflate(getContext(), R.layout.layout_cookie, this);

        layoutCookie = (LinearLayout) findViewById(R.id.cookie);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        btnAction = (TextView) findViewById(R.id.btn_action);
        initDefaultStyle(context);



        layoutCookie.setOnTouchListener(this);
    }


    /**
     * Init the default text color or background color. You can change the default style by set the
     * Theme's attributes.
     * <p>
     * <pre>
     *  <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
     *          <item name="cookieTitleColor">@color/default_title_color</item>
     *          <item name="cookieMessageColor">@color/default_message_color</item>
     *          <item name="cookieActionColor">@color/default_action_color</item>
     *          <item name="cookieBackgroundColor">@color/default_bg_color</item>
     *  </style>
     * </pre>
     */
    private void initDefaultStyle(Context context) {
        //Custom the default style of a cookie
        int titleColor = ThemeResolver.getColor(context, R.attr.cookieTitleColor, Color.WHITE);
        int messageColor = ThemeResolver.getColor(context, R.attr.cookieMessageColor, Color.WHITE);
        int actionColor = ThemeResolver.getColor(context, R.attr.cookieActionColor, Color.WHITE);
        int backgroundColor = ThemeResolver.getColor(context, R.attr.cookieBackgroundColor,
                ContextCompat.getColor(context, R.color.default_bg_color));

        tvTitle.setTextColor(titleColor);
        tvMessage.setTextColor(messageColor);
        btnAction.setTextColor(actionColor);
        layoutCookie.setBackgroundColor(backgroundColor);
    }

    public void setParams(final CookieBar.Params params) {
        if (params != null) {
            duration = params.duration;
            layoutGravity = params.layoutGravity;

            //Icon
            if (params.iconResId != 0) {
                ivIcon.setVisibility(VISIBLE);
                ivIcon.setBackgroundResource(params.iconResId);
                if(params.iconAnimator != null) {
                    params.iconAnimator.setTarget(ivIcon);
                    params.iconAnimator.start();

                }
            }

            //Title
            if (!TextUtils.isEmpty(params.title)) {
                tvTitle.setVisibility(VISIBLE);
                tvTitle.setText(params.title);
                if (params.titleColor != 0) {
                    tvTitle.setTextColor(ContextCompat.getColor(getContext(), params.titleColor));
                }
            }

            //Message
            if (!TextUtils.isEmpty(params.message)) {
                tvMessage.setVisibility(VISIBLE);
                tvMessage.setText(params.message);
                if (params.messageColor != 0) {
                    tvMessage.setTextColor(ContextCompat.getColor(getContext(), params.messageColor));
                }
            }

            //Action
            if (!TextUtils.isEmpty(params.action) && params.onActionClickListener != null) {
                btnAction.setVisibility(VISIBLE);
                btnAction.setText(params.action);
                btnAction.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        params.onActionClickListener.onClick();
                        dismiss();
                    }
                });

                //Action Color
                if (params.actionColor != 0) {
                    btnAction.setTextColor(ContextCompat.getColor(getContext(), params.actionColor));
                }
            }

            //Background
            if (params.backgroundColor != 0) {
                layoutCookie
                        .setBackgroundColor(ContextCompat.getColor(getContext(), params.backgroundColor));
            }

            int padding = getContext().getResources().getDimensionPixelSize(R.dimen.default_padding);
            if (layoutGravity == Gravity.BOTTOM) {
                layoutCookie.setPadding(padding, padding, padding, padding);
            }

            createInAnim();
            createOutAnim();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        viewWidth = getWidth();
        dismissOffsetThreshold = viewWidth / 3;

        if (layoutGravity == Gravity.TOP) {
            super.onLayout(changed, l, 0, r, layoutCookie.getMeasuredHeight());
        } else {
            super.onLayout(changed, l, t, r, b);
        }
    }

    private void createInAnim() {
        slideInAnimation = AnimationUtils.loadAnimation(getContext(),
                layoutGravity == Gravity.BOTTOM ? R.anim.slide_in_from_bottom : R.anim.slide_in_from_top);
        slideInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, duration);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        setAnimation(slideInAnimation);
    }

    private void createOutAnim() {
        slideOutAnimation = AnimationUtils.loadAnimation(getContext(),
                layoutGravity == Gravity.BOTTOM ? R.anim.slide_out_to_bottom : R.anim.slide_out_to_top);
        slideOutAnimationDuration = slideOutAnimation.getDuration();
        slideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void dismiss() {
        dismiss(null);
    }

    public void dismiss(final CookieBarDismissListener listener) {
        if (swipedOut) {
            removeFromParent();
            return;
        }

        slideOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(final Animation animation) {
            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                if (listener != null) {
                    listener.onDismiss();
                }
                removeFromParent();
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {
            }
        });

        long speed = listener == null ? slideOutAnimationDuration : slideOutAnimationDuration / 2;
        slideOutAnimation.setDuration(speed);
        startAnimation(slideOutAnimation);
    }

    private void removeFromParent() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewParent parent = getParent();
                if (parent != null) {
                    Cookie.this.clearAnimation();
                    ((ViewGroup) parent).removeView(Cookie.this);
                }
            }
        }, 200);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialDragX = motionEvent.getRawX();
                return true;

            case MotionEvent.ACTION_UP:
                if (!swipedOut) {
                    view.animate()
                            .x(0)
                            .alpha(1)
                            .setDuration(200)
                            .start();
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if (swipedOut) {
                    return true;
                }
                float offset = motionEvent.getRawX() - initialDragX;
                float alpha = 1 - Math.abs(offset / viewWidth);
                long duration = 0;

                if (Math.abs(offset) > dismissOffsetThreshold) {
                    offset = viewWidth * Math.signum(offset);
                    alpha = 0;
                    duration = 200;
                    swipedOut = true;
                }

                view.animate()
                        .setListener(swipedOut ? getDestroyListener() : null)
                        .x(offset)
                        .alpha(alpha)
                        .setDuration(duration)
                        .start();

                return true;

            default:
                return false;
        }
    }

    private Animator.AnimatorListener getDestroyListener() {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeFromParent();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    public interface CookieBarDismissListener {
        void onDismiss();
    }
}

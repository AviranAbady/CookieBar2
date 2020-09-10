package org.aviran.cookiebarsample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.aviran.cookiebar2.CookieBar;
import org.aviran.cookiebar2.CookieBarDismissListener;
import org.aviran.cookiebar2.OnActionClickListener;

import java.util.concurrent.TimeoutException;

public class JavaDemoFragment extends Fragment {

    private int topCookieCounter = 0;
    private TextView infoTextView;

    public JavaDemoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        this.infoTextView = rootView.findViewById(R.id.infoTextView);

        Button btnTop = rootView.findViewById(R.id.btnTop);
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(getActivity())
                        .setTitle(R.string.top_cookie_title)
                        .setTitleColor(R.color.yellow)
                        .setMessage(getString(R.string.top_cookie_message, ++topCookieCounter))
                        .setIcon(R.drawable.ic_android_white_48dp)
                        .setDuration(5000)
                        .setCookieListener(new CookieBarDismissListener() {

                            @Override
                            public void onDismiss(int dismissType) {
                                String desc = "";
                                switch (dismissType) {
                                    case DismissType.DURATION_COMPLETE:
                                        desc = "Cookie display duration completed";
                                        break;
                                    case DismissType.USER_DISMISS:
                                        desc = "Cookie dismissed by user";
                                        break;
                                    case DismissType.USER_ACTION_CLICK:
                                        desc = "Cookie dismissed by action click";
                                        break;
                                    case DismissType.PROGRAMMATIC_DISMISS:
                                        desc = "Cookie dismissed programmatically";
                                        break;
                                    case DismissType.REPLACE_DISMISS:
                                        desc = "Replaced by new cookie";
                                        break;

                                }

                                infoTextView.setText(desc);
                            }
                        })
                        .show();
            }
        });

        final Button btnBottom = rootView.findViewById(R.id.btnBottom);
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(getActivity())
                        .setDuration(5000)
                        .setTitle(R.string.bottom_cookie_title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.bottom_cookie_message)
                        .setBackgroundColor(R.color.colorPrimary)
                        .setActionColor(R.color.yellow)
                        .setTitleColor(R.color.yellow)
                        .setCookiePosition(CookieBar.BOTTOM)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                               infoTextView.setText(R.string.action_engaged);
                            }
                        })
                        .show();
            }
        });


        Button btnCustomAnimation = rootView.findViewById(R.id.btnCustomAnim);
        btnCustomAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(getActivity())
                        .setTitle(R.string.custom_anim_cookie_title)
                        .setMessage(R.string.custom_anim_cookie_message)
                        .setIcon(R.drawable.ic_android_white_48dp)
                        .setMessageColor(R.color.liteblue)
                        .setBackgroundColor(R.color.orange)
                        .setDuration(5000)
                        .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                        .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                        .show();
            }
        });


        Button btnBottomAnimated = rootView.findViewById(R.id.btnBottomAnimated);
        btnBottomAnimated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(getActivity())
                        .setTitle(R.string.fancy_cookie_title)
                        .setMessage(R.string.fancy_cookie_message)
                        .setIcon(R.drawable.ic_settings_white_48dp)
                        .setIconAnimation(R.animator.iconspin)
                        .setTitleColor(R.color.fancyTitle)
                        .setActionColor(R.color.fancyAction)
                        .setMessageColor(R.color.fancyMessage)
                        .setBackgroundColor(R.color.fancyBackground)
                        .setDuration(5000)
                        .setCookiePosition(CookieBar.BOTTOM)
                        .setAction("OPEN SETTINGS", new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                infoTextView.setText(R.string.action_engaged);
                            }
                        })
                        .show();
            }
        });

        Button btnCustomView = rootView.findViewById(R.id.btnCustomView);
        btnCustomView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                CookieBar.build(getActivity())
                        .setCustomView(R.layout.custom_cookie)
                        .setCustomViewInitializer(new CookieBar.CustomViewInitializer() {
                            @Override
                            public void initView(View view) {

                                Button btnNew = view.findViewById(R.id.custom_cookie_btn_new);
                                Button btnOpen = view.findViewById(R.id.custom_cookie_btn_open);
                                Button btnSave = view.findViewById(R.id.custom_cookie_btn_save);

                                View.OnClickListener btnListener = new View.OnClickListener() {

                                    @Override
                                    public void onClick(View view) {
                                        Button button = (Button) view;
                                        button.setText(R.string.clicked);
                                    }
                                };

                                btnNew.setOnClickListener(btnListener);
                                btnOpen.setOnClickListener(btnListener);
                                btnSave.setOnClickListener(btnListener);
                            }
                        })
                        .setAction("Close", new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                CookieBar.dismiss(getActivity());
                            }
                        })
                        .setTitle(R.string.custom_view_cookie_title)
                        .setMessage(R.string.custom_view_cookie_message)
                        .setEnableAutoDismiss(false)
                        .setSwipeToDismiss(false)
                        .setCookiePosition(Gravity.BOTTOM)
                        .show();
            }
        });

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieBar.dismiss(getActivity());
            }
        });
    }
}
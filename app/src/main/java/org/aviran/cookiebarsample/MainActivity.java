package org.aviran.cookiebarsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.aviran.cookiebar2.CookieBar;
import org.aviran.cookiebar2.OnActionClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTop = findViewById(R.id.btn_top);
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(MainActivity.this)
                        .setTitle(R.string.top_cookie_title)
                        .setTitleColor(R.color.yellow)
                        .setMessage(R.string.top_cookie_message)
                        .setIcon(R.drawable.ic_android_white_48dp)
                        .setDuration(5000)
                        .show();
            }
        });

        final Button btnBottom = findViewById(R.id.btn_bottom);
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(MainActivity.this)
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
                                Toast.makeText(getApplicationContext(), "Action Engaged!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });


        Button btnCustomAnimation = findViewById(R.id.btn_custom_anim);
        btnCustomAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(MainActivity.this)
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


        Button btnBottomAnimated = findViewById(R.id.btn_bottom_animated);
        btnBottomAnimated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.build(MainActivity.this)
                        .setTitle(R.string.fancy_cookie_title)
                        .setMessage(R.string.fancy_cookie_message)
                        .setIcon(R.drawable.ic_settings_white_48dp)
                        .setIconAnimation(R.animator.iconspin)
                        .setTitleColor(R.color.fancyTitle)
                        .setActionColor(R.color.fancyAction)
                        .setMessageColor(R.color.fancyMessage)
                        .setBackgroundColor(R.color.fancyBackground)
                        .setDuration(5000)
                        .setLayoutGravity(Gravity.BOTTOM)
                        .setAction("OPEN SETTINGS", new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Action Engaged!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        Button btnCustomView = findViewById(R.id.btn_custom_view);
        btnCustomView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                CookieBar.build(MainActivity.this)
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
                                CookieBar.dismiss(MainActivity.this);
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

        findViewById(R.id.activity_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CookieBar.dismiss(MainActivity.this);
            }
        });

    }
}

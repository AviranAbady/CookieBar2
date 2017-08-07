package org.aviran.cookiebarsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button btnTop = (Button) findViewById(R.id.btn_top);
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Build(MainActivity.this)
                        .setTitle(R.string.top_cookie_title)
                        .setTitleColor(R.color.yellow)
                        .setMessage(R.string.top_cookie_message)
                        .setIcon(R.drawable.ic_android_white_48dp)
                        .setDuration(5000)
                        .show();
            }
        });

        Button btnBottom = (Button) findViewById(R.id.btn_bottom);
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Build(MainActivity.this)
                        .setDuration(5000)
                        .setTitle(R.string.bottom_cookie_title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.bottom_cookie_message)
                        .setBackgroundColor(R.color.colorPrimary)
                        .setActionColor(R.color.yellow)
                        .setTitleColor(R.color.yellow)
                        .setLayoutGravity(Gravity.BOTTOM)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Action Engaged!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        Button btnBottomAnimated = (Button) findViewById(R.id.btn_bottom_animated);
        btnBottomAnimated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CookieBar.Build(MainActivity.this)
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

        Button btnExtraLarge = (Button) findViewById(R.id.btn_extra_large);
        btnExtraLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CookieBar.Builder(MainActivity.this)
                        .setTitle(R.string.large_cookie_title)
                        .setMessage(R.string.large_cookie_message)
                        .setDuration(5000)
                        .show();
            }
        });

        Button btnPlain = (Button) findViewById(R.id.btn_plain);
        btnPlain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CookieBar.Builder(MainActivity.this)
                        .setTitle(R.string.plain_cookie_title)
                        .setMessage(R.string.plain_cookie_message)
                        .setDuration(5000)
                        .show();
            }
        });
    }
}

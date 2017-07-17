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
                new CookieBar.Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        .setMessage(R.string.cookie_message)
                        .setDuration(5000)
                        .show();
            }
        });

        Button btnBottom = (Button) findViewById(R.id.btn_bottom);
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CookieBar
                        .Builder(MainActivity.this)
                        .setDuration(5000)
                        .setTitle(R.string.cookie_title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage(R.string.cookie_message)
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

        Button btnTopWithIcon = (Button) findViewById(R.id.btn_top_with_icon);
        btnTopWithIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CookieBar.Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        .setIcon(R.mipmap.ic_launcher)
                        .setBackgroundColor(R.color.colorAccent)
                        .setMessage(R.string.cookie_message)
                        .setDuration(5000)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Action Engaged!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });

        Button btnCustom = (Button) findViewById(R.id.btn_custom);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CookieBar.Builder(MainActivity.this)
                        .setTitle(R.string.cookie_title)
                        .setMessage(R.string.cookie_message)
                        .setDuration(5000)
                        .setBackgroundColor(R.color.colorPrimary)
                        .setActionColor(android.R.color.white)
                        .setTitleColor(R.color.yellow)
                        .setAction(R.string.cookie_action, new OnActionClickListener() {
                            @Override
                            public void onClick() {
                                Toast.makeText(getApplicationContext(), "Action Engaged!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .show();
            }
        });
    }
}

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CookieBar2-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6122) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/6474fc01133444e0b6f615d9b1af8589)](https://www.codacy.com/app/AviranAbady/CookieBar2?utm_source=github.com&utm_medium=referral&utm_content=AviranAbady/CookieBar2&utm_campaign=badger) [![AndroidWeekly335](https://raw.githubusercontent.com/AviranAbady/storage/master/AndroidWeekly335.png)](http://androidweekly.net/issues/issue-335)

CookieBar 2
===============
CookieBar is a lightweight library for showing a brief message at the top or bottom of the screen.<br/><br/>

```gradle
implementation 'org.aviran.cookiebar2:cookiebar2:1.1.0'
```

## Screenshot
<img src="https://raw.githubusercontent.com/AviranAbady/storage/master/cookiebar2_1.1.0.gif">

Main differences from the original Cookiebar library are:

* Swipe to dismiss has been added.
* Programmatic dismiss functionality added.
* Only one Cookie can be displayed at a time (New top cookie will dismiss the current top one if exists).
* Message view captures clicks, blocks downward propagation.
* Slightly different build interface.
* Icon animator support.
* Custom layout can be applied to a cookie.
* Colors presets added.
* Root view can be overridden and customized.
* Removed supportRtl / allowBackup from library scope (Set at app level when necessary).

## Create a simple cookie
### Using Kotlin
```kotlin
CookieBar.build(this@MainActivity)
         .setTitle("TITLE")         // String resources are also supported
         .setMessage("MESSAGE")     // i.e. R.string.message
         .show()                    // Cookies are displayed at the top by default
```
### Using Java
```java
CookieBar.build(MainActivity.this)
         .setTitle("TITLE")
         .setMessage("MESSAGE")
         .setCookiePosition(CookieBar.BOTTOM)  // Cookie will be displayed at the bottom
         .show();                              // of the screen
```

## Customize colors, icon, icon animation, call to action button, display duration
```java
CookieBar.build(MainActivity.this)
         .setTitle(R.string.title)
         .setTitleColor(R.color.green)
         .setIcon(R.drawable.icon)
         .setIconAnimation(R.animator.spin)
         .setMessage(R.string.message)
         .setAction(R.string.action_text, new OnActionClickListener() {
             @Override
             public void onClick() {
                // Do something
             }
         })
         .setDuration(5000) // 5 seconds
         .show();
```

## Customize in/out animation
```java
CookieBar.build(MainActivity.this)
         .setTitle(R.string.title)
         .setMessage(R.string.message)
         .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
         .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
         .show();
```

## Programmatically dismiss cookies currently being displayed
```java
CookieBar.dismiss(MainActivity.this);

```


## Create a cookie with a custom layout - Create a dialog!
```java
/* setCustomView -  Set the layout resource for your custom cookie view.
   setCustomViewInitializer - Called after layout inflation, for subview setup. */

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
         .setTitle(R.string.title)
         .setMessage(R.string.message)
         .setEnableAutoDismiss(false) // Cookie will stay on display until manually dismissed
         .setSwipeToDismiss(false)    // Deny dismiss by swiping off the view
         .setCookiePosition(CookieBar.BOTTOM)
         .show();
```

## Change default cookie style by setting theme attributes

```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
   <item name="cookieTitleColor">@color/default_title_color</item>
   <item name="cookieMessageColor">@color/default_message_color</item>
   <item name="cookieActionColor">@color/default_action_color</item>
   <item name="cookieBackgroundColor">@color/default_bg_color</item>
</style>
```

## Dynamically set cookie properties
 * Cookie position (Top/Bottom)
 * Display duration
 * Title text color
 * Body text color
 * Action text color
 * Background color
 * Icon resource
 * Icon animation
 * In/Out animations for the entire view
 * View layout

## Attribution
Forked from the early code base of CookieBar, by Eric Liu.

## License

    Copyright 2018, Aviran Abady.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

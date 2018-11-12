[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CookieBar2-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6122) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/6474fc01133444e0b6f615d9b1af8589)](https://www.codacy.com/app/AviranAbady/CookieBar2?utm_source=github.com&utm_medium=referral&utm_content=AviranAbady/CookieBar2&utm_campaign=badger)

CookieBar 2
===============
CookieBar is a lightweight library for showing a brief message at the top or bottom of the screen.<br/><br/>

```gradle
implementation 'org.aviran.cookiebar2:cookiebar2:1.0.9'
```

## Screenshot
<img src="https://raw.githubusercontent.com/AviranAbady/storage/master/cookiebar2.gif">

Main differences from the original <a href="https://github.com/liuguangqiang/CookieBar/">Cookiebar</a> are:

* Swipe to dismiss has been added.
* Programmatic dismiss functionality added.
* Only one Cookie can be displayed at a time (New top cookie will dismiss the current top one if exists).
* Message view captures clicks, blocks downward propagation.
* Slightly different build interface.
* Icon animator support.
* Custom layout can be applied to a cookie.
* Colors presets added.
* Removed supportRtl / allowBackup from library scope (Should be set at app level).

## Create a simple cookie
### Using Kotlin
```kotlin
CookieBar.build(this@MainActivity)
          .setTitle("TITLE")
          .setMessage("MESSAGE")
          .show()
```
### Using Java
```java
CookieBar.build(MainActivity.this)
         .setTitle("TITLE")
         .setMessage("MESSAGE")
         .show();
```

## Cookie with an icon and an action
```java
CookieBar.build(MainActivity.this)
         .setTitle("TITLE")
         .setIcon(R.drawable.icon)
         .setMessage("MESSAGE")
         .setAction("ACTION", new OnActionClickListener() {
             @Override
             public void onClick() {
             }
         })
         .show();
```

## Programmatically dismiss cookies currently being displayed
```java
CookieBar.dismiss(MainActivity.this);

```


## Create a cookie with a custom layout
```java
// The view you inflate must contain subviews with the same id's as the original layout_cookie.xml
// You can set visibility to gone, if you wish to hide them.
final View customView =
                        LayoutInflater.
                        from(getContext()).
                        inflate(R.layout.custom_cookie, null);

final ProgressBar progressBar = customView.findViewById(R.id.cookiebar_progressbar);

CookieBar.build(MainActivity.this)
         .setCustomView(customView)
         .setTitle(R.string.plain_cookie_title)
         .setMessage(R.string.plain_cookie_message)
         .setDuration(10000)
         .setAction("Hide Progress bar", new OnActionClickListener() {
                  @Override
                  public void onClick() {
                           progressBar.setVisibility(View.GONE);
                  }
         })
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

## Dynamically set cookie style
 * layoutGravity
 * backgroundColor
 * titleColor
 * messageColor
 * actionColor
 * duration

## License

    Copyright 2017

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

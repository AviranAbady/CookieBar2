CookieBar 2
===============
CookieBar is a lightweight library for showing a brief message at the top or bottom of the screen.<br/><br/>
This is a fork of <a href="https://github.com/liuguangqiang/CookieBar/">Cookiebar</a><br/><br/>
Main differences are:

* Swipe to dismiss has been added.
* Only one Cookie can be displayed at a time (New top cookie will dismiss the current top one if exists).
* Slightly different build interface.
* Icon animator support.
* Colors presets added.
* Removed supportRtl from library scope (Should be set at app level).

```gradle
compile 'org.aviran.cookiebar2:cookiebar2:1.0.5'
```

## Screenshot
<img src="https://raw.githubusercontent.com/AviranAbady/storage/master/cookiebar2.gif">

## Create a simple cookie
```java
CookieBar.Build(MainActivity.this)
         .setTitle("TITLE")
         .setMessage("MESSAGE")
         .show();
```

## Cookie with an icon and an action
```java
CookieBar.Build(MainActivity.this)
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

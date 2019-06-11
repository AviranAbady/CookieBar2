package org.aviran.cookiebar2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public interface CookieBarDismissListener {

    @Retention(RetentionPolicy.SOURCE)
    @interface DismissType {
        int DURATION_COMPLETE = 0;
        int USER_DISMISS = 1;
        int USER_ACTION_CLICK = 2;
        int PROGRAMMATIC_DISMISS = 3;
        int REPLACE_DISMISS = 4;
    }

    void onDismiss(@DismissType int dismissType);
}

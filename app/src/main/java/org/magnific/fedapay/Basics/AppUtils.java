package org.magnific.fedapay.Basics;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/*
 *  Created by CodeToHope Benin Team.
 *  Email shounsa@codetohope.org
 *  Copyright (c) 2018 CodeToHope. All rights reserved.
 */

public class AppUtils {

    public static void hideSoftKeyboard(Activity activity) {
        final InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            if (activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

}

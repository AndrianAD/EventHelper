package com.druk.eventhelper;

import android.widget.EditText;

public class UtilClass {
    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}

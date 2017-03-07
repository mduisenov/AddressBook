package com.taxsee.ui.helper;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;

import com.taxsee.DemoApp;

import no.taxsee.addressbook.R;

public class UiHelper {
    public static boolean isEmptyField(EditText editText, TextInputLayout textInputLayout) {
        String errorText = DemoApp.getContext().getString(R.string.empty_field);
        boolean isEmpty = TextUtils.isEmpty(editText.getText().toString());
        if (isEmpty) {
            setEditTextError(errorText, editText, textInputLayout);
        }
        return isEmpty;
    }
    public static boolean isEmptyField(EditText editText) {
        return isEmptyField(editText);
    }

    public static void setEditTextError(String errorText, EditText editText) {
        setEditTextError(errorText, editText, null);
    }

    public static void setEditTextError(String errorText, EditText editText, TextInputLayout textInputLayout) {
        if (textInputLayout == null) {
            editText.setError(errorText);
            return;
        }
        textInputLayout.setError(errorText);
        textInputLayout.setErrorEnabled(true);
    }
}

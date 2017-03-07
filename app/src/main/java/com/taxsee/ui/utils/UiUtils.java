package com.taxsee.ui.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import no.taxsee.addressbook.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static butterknife.ButterKnife.findById;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public class UiUtils {

    public static Dialog getProgressDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_wait, null);
        TextView messageView = findById(view, R.id.message);
        messageView.setText(R.string.wait);
        AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .setCancelable(false)
                .setView(view)
                .create();
        alertDialog.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
        return alertDialog;
    }

    public static Snackbar getSnackBar(View view, String msg, int duration, View.OnClickListener listener, String actionMessage) {
        Snackbar snackbar = Snackbar.make(view, msg, duration).setDuration(duration).setActionTextColor(-1);
        if (!(listener == null || TextUtils.isEmpty(actionMessage))) {
            snackbar.setAction(actionMessage, listener);
        }
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.textColorGray));
        TextView tv = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        Button btn = (Button) snackbar.getView().findViewById(R.id.snackbar_action);
        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTypeface(null, 1);
        tv.setTextColor(Color.WHITE);
        btn.setTextColor(Color.CYAN);
        return snackbar;
    }

    public static Snackbar getSnackBar(View view, String msg, View.OnClickListener onClickListener) {
        return getSnackBar(view, msg, Snackbar.LENGTH_LONG, null, view.getContext().getString(R.string.retry));
    }

    public static Snackbar getSnackBar(View view, String msg) {
        return getSnackBar(view, msg, Snackbar.LENGTH_LONG, null, null);
    }

    public static void hideSoftKeyboard(final Activity activity) {
        final View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }
}

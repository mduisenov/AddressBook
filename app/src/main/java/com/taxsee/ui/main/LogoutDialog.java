package com.taxsee.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.taxsee.data.prefs.SecureStringPreference;
import com.taxsee.data.repository.auth.PasswordPref;
import com.taxsee.data.repository.auth.UserNamePref;
import com.taxsee.ui.auth.AuthActivity;
import com.taxsee.ui.base.BaseDialogFragment;

import javax.inject.Inject;

import no.taxsee.addressbook.R;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */
public class LogoutDialog extends BaseDialogFragment {

    @Inject
    @UserNamePref
    SecureStringPreference mUserNamePref;
    @Inject
    @PasswordPref
    SecureStringPreference mPasswordPref;

    private DialogInterface.OnClickListener mPositiveBtnListener;
    private DialogInterface.OnClickListener mNegativeBtnListener;

    public LogoutDialog() {
       initListeners();
    }

    public static DialogFragment newInstance() {
        return new LogoutDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle bundle) {
        return new AlertDialog.Builder( getActivity())
                .setMessage(R.string.logout_message)
                .setTitle(R.string.logout_title)
                .setPositiveButton(R.string.yes, mPositiveBtnListener)
                .setNegativeButton(R.string.no, mNegativeBtnListener)
                .create();
    }

    private void initListeners() {
        mPositiveBtnListener = (dialogInterface, positiveButtonListener) -> {
            mUserNamePref.delete();
            mPasswordPref.delete();
            AuthActivity.startActivity(getActivity(), true);
            getActivity().finish();
            dismiss();
        };
        mNegativeBtnListener = (dialogInterface, positiveButtonListener) -> dismiss();
    }
}

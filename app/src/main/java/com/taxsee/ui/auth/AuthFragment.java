package com.taxsee.ui.auth;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taxsee.presenter.modules.auth.AuthPresenter;
import com.taxsee.presenter.modules.auth.AuthView;
import com.taxsee.ui.base.BaseFragment;
import com.taxsee.ui.helper.UiHelper;
import com.taxsee.ui.main.MainActivity;
import com.taxsee.ui.utils.UiUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import no.taxsee.addressbook.R;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class AuthFragment extends BaseFragment implements AuthView {

    private Dialog mProgressDialog;

    @BindView(R.id.textInputLayoutLogin)    TextInputLayout mTextInputLayoutLogin;
    @BindView(R.id.loginEdit)    TextInputEditText mTextInputEditTextLogin;
    @BindView(R.id.textInputLayoutPassword)    TextInputLayout mTextInputLayoutPassword;
    @BindView(R.id.passwordEdit)    TextInputEditText mTextInputEditTextPassword;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @Inject AuthPresenter mAuthPresenter;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mProgressDialog = UiUtils.getProgressDialog(getContext());
        return inflater.inflate(R.layout.fragment_auth, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSupportActionBar(mToolbar);
        mAuthPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        mAuthPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mProgressDialog.dismiss();
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }

    @Override
    public void showError(String error) {
        UiUtils.getSnackBar(getView(), error).show();
    }

    @OnClick(R.id.auth)
    public void onAuthClick() {
        String login = mTextInputEditTextLogin.getText().toString();
        String password = mTextInputEditTextPassword.getText().toString();
        if (isValidCredentials())  {
            UiUtils.hideSoftKeyboard(getActivity());
            mAuthPresenter.signIn(login, password);
        }
    }

    private boolean isValidCredentials() {
        if (UiHelper.isEmptyField(mTextInputEditTextLogin, mTextInputLayoutLogin)) {
            return false;
        }
        if (UiHelper.isEmptyField(mTextInputEditTextPassword, mTextInputLayoutPassword)) {
            return false;
        }
        return true;
    }

    @Override
    public void openMainWindow() {
        MainActivity.startActivity(getActivity(), true);
    }
}

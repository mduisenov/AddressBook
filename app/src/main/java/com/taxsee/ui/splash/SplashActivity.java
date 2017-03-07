package com.taxsee.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.crashlytics.android.Crashlytics;
import com.taxsee.data.prefs.secure.SecureStringPreference;
import com.taxsee.data.repository.auth.PasswordPref;
import com.taxsee.data.repository.auth.UserNamePref;
import com.taxsee.presenter.modules.splash.SplashPresenter;
import com.taxsee.presenter.modules.splash.SplashView;
import com.taxsee.ui.auth.AuthActivity;
import com.taxsee.ui.base.BaseActivity;
import com.taxsee.ui.main.MainActivity;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import no.taxsee.addressbook.R;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject    SplashPresenter mSplashPresenter;
    @Inject
    @UserNamePref
    SecureStringPreference mUserNamePref;
    @Inject
    @PasswordPref
    SecureStringPreference mPasswordPref;

    public static Intent getIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void loadDone() {
        if (mUserNamePref.isSet() && mPasswordPref.isSet()) {
            MainActivity.startActivity(this, true);
        } else {
            startActivity(AuthActivity.getIntent(this));
        }
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Fabric.with(this, new Crashlytics());
        setCustomContentView(R.layout.activity_splash);
        mSplashPresenter.attachView(this);
        mSplashPresenter.syncPreloadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.detachView();
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void showLoading() {
    }
}


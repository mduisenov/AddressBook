package com.taxsee.presenter.modules.auth;

import android.util.Log;

import com.taxsee.data.model.auth.AuthResponse;
import com.taxsee.data.prefs.SecureStringPreference;
import com.taxsee.data.repository.auth.PasswordPref;
import com.taxsee.data.repository.auth.UserNamePref;
import com.taxsee.domain.repository.AuthRepository;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class AuthPresenterImpl extends AuthPresenter {

    private Subscription mSubscription;
    private final AuthRepository mAuthRepository;
    private final SecureStringPreference mPasswordPref;
    private final SecureStringPreference mUserNamePref;

    @Inject
    public AuthPresenterImpl(AuthRepository authRepository,
                             @PasswordPref SecureStringPreference passwordPref,
                             @UserNamePref SecureStringPreference userNamePref) {
        mAuthRepository = authRepository;
        mPasswordPref = passwordPref;
        mUserNamePref = userNamePref;
    }

    @Override
    public void onViewAttached() {

    }

    @Override
    public void signIn(String login, String password) {
        getView().showLoading();
        mSubscription = mAuthRepository.signIn(login, password).subscribe(new Subscriber<AuthResponse>() {
            @Override
            public void onCompleted() {
                Timber.d("Complete");
                getView().hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("error %s", Log.getStackTraceString(e));
                getView().hideLoading();
                getView().showError(e.getMessage());
            }

            @Override
            public void onNext(AuthResponse authResponse) {
                Timber.d("onNext %s", authResponse);
                if(authResponse.isSuccess()){
                    mPasswordPref.set(password);
                    mUserNamePref.set(login);
                    getView().openMainWindow();
                }else{
                    getView().showError(authResponse.getMessage());
                }
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

}

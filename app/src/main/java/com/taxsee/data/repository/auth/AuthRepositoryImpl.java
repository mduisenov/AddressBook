package com.taxsee.data.repository.auth;

import com.taxsee.data.model.auth.AuthResponse;
import com.taxsee.data.net.AuthService;
import com.taxsee.domain.repository.AuthRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthService mAuthService;

    @Inject
    public AuthRepositoryImpl(AuthService authService) {
        mAuthService = authService;
    }

    @Override
    public Observable<AuthResponse> signIn(String login,  String password) {
        return mAuthService.signIn(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error);
    }
}

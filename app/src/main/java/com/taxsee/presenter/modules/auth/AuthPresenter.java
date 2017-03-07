package com.taxsee.presenter.modules.auth;

import com.taxsee.presenter.base.Presenter;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public abstract class AuthPresenter extends Presenter<AuthView> {
    public abstract void signIn(String phoneNumber, String password);
}

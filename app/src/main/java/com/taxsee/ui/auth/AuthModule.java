package com.taxsee.ui.auth;

import com.taxsee.presenter.modules.auth.AuthPresenter;
import com.taxsee.presenter.modules.auth.AuthPresenterImpl;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Marat Duisenov on 23.02.2017.
 */
@Module(
        complete = false,
        library = true,
        injects = {AuthActivity.class,
                   AuthFragment.class})
public class AuthModule {
    @Provides
    AuthPresenter provideAuthPresenter(AuthPresenterImpl authPresenter) {
        return authPresenter;
    }
}

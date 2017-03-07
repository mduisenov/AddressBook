package com.taxsee.ui.splash;

import com.taxsee.presenter.modules.splash.SplashPresenter;
import com.taxsee.presenter.modules.splash.SplashPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                SplashActivity.class,
        },

        complete = false,
        library = true
)
public final class SplashModule {
    @Provides
    SplashPresenter provideViewContainer(SplashPresenterImpl splashPresenterImpl) {
        return splashPresenterImpl;
    }
}


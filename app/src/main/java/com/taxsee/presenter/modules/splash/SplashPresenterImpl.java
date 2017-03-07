package com.taxsee.presenter.modules.splash;

import javax.inject.Inject;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */
public class SplashPresenterImpl extends SplashPresenter {

    @Inject
    public SplashPresenterImpl() {
    }

    @Override
    public void syncPreloadData() {
        // TODO: preload data
        getView().loadDone();
    }

    @Override
    public void onViewAttached() {

    }
}

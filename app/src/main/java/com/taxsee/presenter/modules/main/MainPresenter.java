package com.taxsee.presenter.modules.main;

import com.taxsee.presenter.base.Presenter;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */
public abstract class MainPresenter extends Presenter<MainView> {

    abstract public void fetchAll();
}

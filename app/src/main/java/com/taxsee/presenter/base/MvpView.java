package com.taxsee.presenter.base;

/**
 * Interface representing a View that will use to load data.
 */
public interface MvpView {
    void showLoading();
    void hideLoading();
    void showError(String error);
}
package com.taxsee.presenter.modules.main;

import android.util.Log;

import com.taxsee.data.prefs.secure.SecureStringPreference;
import com.taxsee.data.repository.auth.PasswordPref;
import com.taxsee.data.repository.auth.UserNamePref;
import com.taxsee.domain.entity.Department;
import com.taxsee.domain.repository.CommonContentRepository;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import tellh.com.recyclertreeview_lib.TreeNode;
import timber.log.Timber;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public class MainPresenterImpl extends MainPresenter {

    private Subscription mSubscription;
    private final CommonContentRepository mCommonContentRepository;
    private final SecureStringPreference mUserNamePref;
    private final  SecureStringPreference mPasswordPref;

    @Inject
    public MainPresenterImpl(CommonContentRepository commonContentRepository,
                             @UserNamePref SecureStringPreference userNamePref,
                             @PasswordPref SecureStringPreference passwordPref) {
        mCommonContentRepository = commonContentRepository;
        mUserNamePref = userNamePref;
        mPasswordPref = passwordPref;
    }

    @Override
    public void onViewAttached() {
        getView().initUi();
    }

    @Override
    public void fetchAll() {
        getView().showLoading();
        mSubscription = mCommonContentRepository.getAll(mUserNamePref.get() ,mPasswordPref.get())
                        .subscribe(new Subscriber<TreeNode<Department>>() {

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
            public void onNext(TreeNode<Department> treeNode) {
                Timber.d("onNext %s", treeNode);
                getView().showAll(treeNode);
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        if(mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}

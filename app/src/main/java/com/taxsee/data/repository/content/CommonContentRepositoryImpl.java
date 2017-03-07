package com.taxsee.data.repository.content;

import com.taxsee.data.exception.NetworkConnectionException;
import com.taxsee.data.net.ContentService;
import com.taxsee.domain.entity.Department;
import com.taxsee.domain.repository.CommonContentRepository;

import java.io.IOException;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tellh.com.recyclertreeview_lib.TreeNode;


/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class CommonContentRepositoryImpl implements CommonContentRepository {
    private final ContentService mContentService;

    @Inject
    public CommonContentRepositoryImpl(ContentService contentService) {
        mContentService = contentService;
    }

    @Override
    public Observable<TreeNode<Department>> getAll(String login, String password) {
        return mContentService.getAll(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof IOException) {
                        return Observable.error(new NetworkConnectionException(throwable));
                    } else {
                        return Observable.error(throwable);
                    }

                });
    }
}

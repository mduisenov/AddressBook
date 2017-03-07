package com.taxsee.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.taxsee.DemoApp;
import com.taxsee.ui.misc.NetworkProgressView;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public abstract class BaseListFragment extends BaseFragment {

    protected NetworkProgressView mNetworkProgressView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DemoApp.obtain().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNetworkProgressView = new NetworkProgressView(getContext());
        ((ViewGroup) view).addView(mNetworkProgressView);
    }
}

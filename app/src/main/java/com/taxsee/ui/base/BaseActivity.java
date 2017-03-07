package com.taxsee.ui.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.taxsee.DemoApp;
import com.taxsee.ui.ViewContainer;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class BaseActivity extends AppCompatActivity {

    @Inject ViewContainer viewContainer;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        DemoApp.obtain().inject(this);
    }

    protected void setCustomContentView(@LayoutRes int resId) {
        ViewGroup container = viewContainer.forActivity(this);

        getLayoutInflater().inflate(resId, container);
        ButterKnife.bind(this, container);
    }

    protected void changeFragment(Fragment f, @IdRes int containerId, boolean addBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(containerId, f);
        if (addBackStack) {
            transaction.addToBackStack("null");
        }
        transaction.commitAllowingStateLoss();
    }

    protected void changeFragment(Fragment f, @IdRes int containerId) {
        changeFragment(f, containerId, false);
    }
}

package com.taxsee.ui.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taxsee.data.prefs.secure.SecureStringPreference;
import com.taxsee.data.repository.auth.UserNamePref;
import com.taxsee.ui.base.BaseFragment;
import com.taxsee.ui.bus.RxBus;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import no.taxsee.addressbook.R;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public class SideMenuDrawerFragment extends BaseFragment {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    @Inject @UserNamePref
    SecureStringPreference mUserNamePref;

    @BindView(R.id.navigationView) NavigationView mNavigationView;

    @Inject Picasso mPicasso;
    @Inject RxBus mRxBus;


    public void setupDrawer(DrawerLayout upDrawer, Toolbar toolbar) {
        this.mDrawerLayout = upDrawer;
        mToolbar = toolbar;
        setUpNavigationDrawer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_side_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initView() {
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isCheckable())
                menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            mRxBus.post(new MainActivityEvents.OnSideMenuItemClicked(menuItem.getItemId()));
            return true;
        });

        NavigationDrawerHeaderView headerView = new NavigationDrawerHeaderView(getActivity());

        headerView.setUserLogin(mUserNamePref.get());

        mNavigationView.addHeaderView(headerView);
    }

    private void setUpNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, 0, 0);

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(() -> mDrawerToggle.syncState());
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }
}

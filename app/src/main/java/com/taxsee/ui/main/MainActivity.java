package com.taxsee.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.taxsee.ui.base.BaseActivity;
import com.taxsee.ui.bus.RxBus;

import javax.inject.Inject;

import butterknife.BindView;
import no.taxsee.addressbook.R;
import rx.Subscription;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public final class MainActivity extends BaseActivity {

    @BindView(R.id.main_content)
    ViewGroup mContent;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;

    @Inject
    RxBus mRxBus;
    private Subscription mSideMenuSubscription;

    public static Intent getIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        return i;
    }

    public static void startActivity(Context context, boolean clearTask) {
        Intent intent = new Intent(context, MainActivity.class);
        if (clearTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private void initListeners() {
        mSideMenuSubscription = mRxBus.register(MainActivityEvents.OnSideMenuItemClicked.class,
                onSideMenuItemClicked -> {
                    openMenuCategory(onSideMenuItemClicked.getMenuId());
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.activity_main);
        setupToolbar();
        setupSideMenu();
        initListeners();
        initView();
    }

    private void initView() {
        changeFragment(MainFragment.newInstance(), R.id.main_content);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupSideMenu() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_menu_fragment);
        if (f instanceof SideMenuDrawerFragment) {
            ((SideMenuDrawerFragment) f).setupDrawer(mDrawerLayout, mToolbar);
        }
    }

    private void openMenuCategory(int menuId) {
        switch (menuId) {
            case R.id.menuLogout:
                LogoutDialog.newInstance().show(getSupportFragmentManager(), null);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSideMenuSubscription.unsubscribe();
    }

}

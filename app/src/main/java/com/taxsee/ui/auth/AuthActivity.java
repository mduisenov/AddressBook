package com.taxsee.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.taxsee.ui.base.BaseActivity;
import com.taxsee.ui.bus.RxBus;

import javax.inject.Inject;

import no.taxsee.addressbook.R;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class AuthActivity extends BaseActivity {

    @Inject
    RxBus mRxBus;

    public static Intent getIntent(Context context) {
        Intent i = new Intent(context, AuthActivity.class);
        return i;
    }

    public static void startActivity(Context context, boolean clearTask) {
        Intent intent = new Intent(context, AuthActivity.class);
        if (clearTask) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.activity_auth);
        changeFragment(AuthFragment.newInstance(), R.id.container);

    }

}

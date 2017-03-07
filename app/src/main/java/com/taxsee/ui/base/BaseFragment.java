package com.taxsee.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.taxsee.DemoApp;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public class BaseFragment extends Fragment {
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DemoApp.obtain().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void setSupportActionBar(Toolbar toolbar){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}

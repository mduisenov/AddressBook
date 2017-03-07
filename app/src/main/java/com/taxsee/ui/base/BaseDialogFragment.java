package com.taxsee.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taxsee.DemoApp;
import com.taxsee.ui.bus.RxBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    @Inject
    protected RxBus mBus;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        DemoApp.obtain().inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected View inflateDialogView(int res, ViewGroup root) {
        View v = LayoutInflater.from(getActivity()).inflate(res, root);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    protected View inflateDialogView(LayoutInflater inflater, @LayoutRes int res, ViewGroup root) {
        View view = inflater.inflate(res, root, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!= null){
            try{
                unbinder.unbind();
            }catch(IllegalStateException ignored){
                Timber.e(Log.getStackTraceString(ignored));
            }
        }
    }

    protected void setSupportActionBar(Toolbar toolbar){
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }
}

package com.taxsee.ui.misc;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.taxsee.addressbook.R;


public class NetworkProgressView extends FrameLayout {

    private OnClickListener mOnRetryClickListener;

    @BindView(R.id.oopsMessage)
    TextView mTextViewOopsMessage;

    @BindView(R.id.oopsLayout)
    View oopsLayout;

    @BindView(R.id.progressLayout)
    View progressLayout;

    public NetworkProgressView(Context context) {
        super(context);
        inflate(getContext(), R.layout.layout_network_progress, this);
        ButterKnife.bind(this);
        setLayoutParams(generateDefaultLayoutParams());
    }

    public NetworkProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.layout_network_progress, this);
        ButterKnife.bind(this);
        setLayoutParams(generateDefaultLayoutParams());
    }

    public NetworkProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.layout_network_progress, this);
        ButterKnife.bind(this);
        setLayoutParams(generateDefaultLayoutParams());
    }

    @SuppressWarnings("unused")
    @TargetApi(21)
    public NetworkProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflate(getContext(), R.layout.layout_network_progress, this);
        ButterKnife.bind(this);
        setLayoutParams(generateDefaultLayoutParams());
    }

    public void retry() {
        oopsLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);
    }

    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        mOnRetryClickListener = onRetryClickListener;
    }

    public void onError() {
        onError(null);
    }

    public void onError(String message) {
        if (!TextUtils.isEmpty(message)) {
            mTextViewOopsMessage.setText(message);
        }
        oopsLayout.setVisibility(View.VISIBLE);
        progressLayout.setVisibility(View.GONE);
    }

    public void onSuccess() {
        setVisibility(View.GONE);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @OnClick(R.id.buttonRetry)
    public void onRetryButtonClicked(View v) {
        if (mOnRetryClickListener != null) {
            mOnRetryClickListener.onClick(v);
        }
    }
}

package com.taxsee.ui.main.employee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.taxsee.data.net.ApiModule;
import com.taxsee.data.prefs.secure.SecureStringPreference;
import com.taxsee.data.repository.auth.PasswordPref;
import com.taxsee.data.repository.auth.UserNamePref;
import com.taxsee.domain.entity.Employee;
import com.taxsee.ui.base.BaseActivity;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import no.taxsee.addressbook.R;
import timber.log.Timber;

/**
 * Created by Marat Duisenov on 25.02.2017.
 */

public class EmployeeActivity extends BaseActivity {

    private static final String EXTRA_EMPLOYEE = "employee";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.textName) TextView mTextName;
    @BindView(R.id.textTitle) TextView mTextTitle;
    @BindView(R.id.textPhone) TextView mTextPhone;
    @BindView(R.id.textEmail) TextView mTextEmail;

    @BindView(R.id.toolbar_image) ImageView mImageEmployee;

    @Inject Picasso mPicasso;
    @Inject
    @UserNamePref
    SecureStringPreference mUserNamePref;
    @Inject
    @PasswordPref
    SecureStringPreference mPasswordPref;

    private Employee mEmployee;

    public static Intent getIntent(Context context, Employee employee) {
        Timber.e("Asses GET INTENT %s", employee);
        Intent intent = new Intent(context, EmployeeActivity.class);
        intent.putExtra(EXTRA_EMPLOYEE, employee);
        return intent;
    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initEmployeeViews();
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        Timber.e("Asses ON CREATE %s", bundle);
        super.onCreate(bundle);
        setCustomContentView(R.layout.activity_employee);
        initData();
        initViews();
    }

    private void initData() {
        mEmployee = (Employee) getIntent().getSerializableExtra((EXTRA_EMPLOYEE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @OnClick(R.id.fab)
    void onCallClick(){
        if (!TextUtils.isEmpty(mEmployee.getPhone())) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+ mEmployee.getPhone()));
            startActivity(intent);
        }else{
            Toast.makeText(this, "No phone number", Toast.LENGTH_SHORT).show();
        }

    }

    private void initEmployeeViews() {
        if (!TextUtils.isEmpty(mEmployee.getName())) {
            mTextName.setText(mEmployee.getName());
        }
        if (!TextUtils.isEmpty(mEmployee.getTitle())) {
            mTextTitle.setText(mEmployee.getTitle());
        }
        if (!TextUtils.isEmpty(mEmployee.getPhone())) {
            mTextPhone.setText(mEmployee.getPhone());
        }
        if (!TextUtils.isEmpty(mEmployee.getEmail())) {
            mTextEmail.setText(mEmployee.getEmail());
        }
        setAvatar(generateAvatarUrl(mEmployee.getId()));
    }

    private String generateAvatarUrl(String id) {
        return ApiModule.EMPLOYEE_IMAGE_URL + "login=" + mUserNamePref.get() +
                "&password=" + mPasswordPref.get() +
                "&id=" + id;
    }

    public void setAvatar(String avatar) {
        Timber.d("setAvatar %s", avatar);
        if (TextUtils.isEmpty(avatar)) {
            return;
        }
        if (!URLUtil.isValidUrl(avatar)) {
            mPicasso.load(new File(avatar)).into(mImageEmployee);
            return;
        }
        mPicasso.load(avatar).into(mImageEmployee);
    }

}

package com.taxsee.presenter.modules.main.employee;

import com.taxsee.presenter.base.Presenter;

/**
 * Created by Marat Duisenov on 27.02.2017.
 */

public abstract class EmployeePresenter extends Presenter<EmployeeView> {

    public abstract void setAvatar(String avatar);

    public abstract void setName(String name);

    public abstract void setTitle(String title);

    public abstract void setPhone(String phone);

    public abstract void setEmail(String email);
}

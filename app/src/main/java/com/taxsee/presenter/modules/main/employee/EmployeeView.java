package com.taxsee.presenter.modules.main.employee;

import com.taxsee.presenter.base.MvpView;

/**
 * Created by Marat Duisenov on 27.02.2017.
 */

public interface EmployeeView extends MvpView {

  public void setAvatar(String avatar);

  public void setName(String name);

  public void setTitle(String title);

  public void setPhone(String phone);

  public void setEmail(String email);
}

package com.taxsee.presenter.modules.main;

import com.taxsee.domain.entity.Department;
import com.taxsee.presenter.base.MvpView;

import tellh.com.recyclertreeview_lib.TreeNode;

public interface MainView extends MvpView {

  void initUi();
  void showAll(TreeNode<Department> treeNode);
}

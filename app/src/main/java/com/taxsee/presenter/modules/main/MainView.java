package com.taxsee.presenter.modules.main;

import com.taxsee.domain.entity.Department;
import com.taxsee.presenter.base.MvpView;

import tellh.com.recyclertreeview_lib.TreeNode;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public interface MainView extends MvpView {

  void initUi();
  void showAll(TreeNode<Department> treeNode);
}

package com.taxsee.domain.repository;

import com.taxsee.domain.entity.Department;

import rx.Observable;
import tellh.com.recyclertreeview_lib.TreeNode;


/**
 * Created by Marat Duisenov on 23.02.2017.
 */
public interface CommonContentRepository {
    Observable<TreeNode<Department>> getAll(String login, String password);
}

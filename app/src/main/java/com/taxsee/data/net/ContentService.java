package com.taxsee.data.net;

import com.taxsee.domain.entity.Department;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import tellh.com.recyclertreeview_lib.TreeNode;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public interface ContentService {
    @GET("/Contacts.svc/GetAll")
    Observable<TreeNode<Department>> getAll(@Query("login") String login,
                                            @Query("password") String password);
}

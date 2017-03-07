package com.taxsee.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import no.taxsee.addressbook.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by Marat Duisenov on 22.02.2017.
 */

public class Employee implements LayoutItemType, Serializable{

    @SerializedName("ID")
    private String id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Title")
    private String title;

    @SerializedName("Email")
    private String email;

    @SerializedName("Phone")
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_employee;
    }
}

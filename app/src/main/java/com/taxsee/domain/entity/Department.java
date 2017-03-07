package com.taxsee.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import no.taxsee.addressbook.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by Marat Duisenov on 22.02.2017.
 */
public class Department implements LayoutItemType, Serializable {

    @SerializedName("ID")
    private String id;

    @SerializedName("Name")
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Department() {
    }

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

    @Override
    public int getLayoutId() {
        return R.layout.item_department;
    }
}

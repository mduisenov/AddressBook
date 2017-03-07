package com.taxsee.data.model.content;

import com.taxsee.domain.entity.Department;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Marat Duisenov on 23.02.2017.
 */

public class ContentResponse implements Serializable{

    @SerializedName("ID")
    private String id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Name")
    private List<Department> departments;

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

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}

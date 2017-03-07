package com.taxsee.util;

import com.taxsee.domain.entity.Department;
import com.taxsee.domain.entity.Employee;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import tellh.com.recyclertreeview_lib.TreeNode;


/**
 * Created by Marat Duisenov on 27.02.2017.
 */

public class TreeNodeDeserializer implements JsonDeserializer<TreeNode<Department>> {

    @Override
    public TreeNode<Department> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject1 = (JsonObject) json;
        TreeNode<Department> treeNode = new TreeNode<>(parseDepartment(jsonObject1));
        JsonArray jsonArray = jsonObject1.getAsJsonArray("Departments");

        generateParentTree(jsonArray, treeNode);

        return treeNode;
    }

    /**
     * Generate parent of tree and parse child
     *
     * @param jsonArray
     * @param treeNode
     */
    private void generateParentTree(JsonArray jsonArray, TreeNode treeNode) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject parentJsonObject = jsonArray.get(i).getAsJsonObject();
            TreeNode<Department> parentTreeNode = new TreeNode<>(parseDepartment(parentJsonObject));
            treeNode.addChild(parentTreeNode);
            parseChild(parentJsonObject, parentTreeNode);
        }
    }

    /**
     * recursively parse a child until not getting to leaves
     *
     * @param jsonObject
     * @param parentTreeNode
     */
    private void parseChild(JsonObject jsonObject, TreeNode parentTreeNode) {
        if (jsonObject.has("Employees")) {
            JsonArray leafJsonArray= jsonObject.getAsJsonArray("Employees");
            for (int j = 0; j < leafJsonArray.size(); j++) {
                JsonObject leafJsonObject = leafJsonArray.get(j).getAsJsonObject();
                TreeNode<Employee> leafTreeNode = new TreeNode<>(parseEmployee(leafJsonObject));
                parentTreeNode.addChild(leafTreeNode);
            }
        } else if (jsonObject.has("Departments")) {
            JsonArray childJsonArray = jsonObject.getAsJsonArray("Departments");
            for (int j = 0; j < childJsonArray.size(); j++) {
                JsonObject childJsonObject = childJsonArray.get(j).getAsJsonObject();
                TreeNode<Department> childTreeNode = new TreeNode<>(parseDepartment(childJsonObject));
                parentTreeNode.addChild(childTreeNode);
                parseChild(childJsonObject, childTreeNode);
            }
        }
    }

    /**
     * Department parser object parser
     *
     * @param jsonObject - json object which will be parsed into Department
     * @return instance of parsed Department
     */
    private Department parseDepartment(JsonObject jsonObject) {
        Department department = new Department();
        department.setId(jsonObject.get("ID").getAsString());
        department.setName(jsonObject.get("Name").getAsString());
        return department;
    }

    /**
     * Employee parser object parse
     *
     * @param jsonObject - json object which will be parsed into Employee
     * @return instance of parsed Employee
     */
    private Employee parseEmployee(JsonObject jsonObject) {
        Employee employee = new Employee();
        employee.setId(jsonObject.get("ID").getAsString());
        employee.setName(jsonObject.has("Name") ? jsonObject.get("Name").getAsString() : "");
        employee.setTitle(jsonObject.has("Title") ? jsonObject.get("Title").getAsString() : "");
        employee.setEmail(jsonObject.has("Email") ? jsonObject.get("Email").getAsString() : "");
        employee.setPhone(jsonObject.has("Phone") ? jsonObject.get("Phone").getAsString() : "");
        return employee;
    }
}

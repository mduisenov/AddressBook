package com.taxsee.ui.main;

import android.view.View;
import android.widget.TextView;

import com.taxsee.domain.entity.Employee;

import no.taxsee.addressbook.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by Marat Duisenov on 26.02.2017.
 */

public class EmployeeViewBinder extends TreeViewBinder<EmployeeViewBinder.ViewHolder> {

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        Employee employeeNode = (Employee) node.getContent();
        holder.textTitle.setText(employeeNode.getTitle());
        holder.textName.setText(employeeNode.getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_employee;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {
        public TextView textTitle;
        public TextView textName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.textTitle = (TextView) rootView.findViewById(R.id.textTitle);
            this.textName = (TextView) rootView.findViewById(R.id.textName);
        }

    }
}

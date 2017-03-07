package com.taxsee.ui.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.taxsee.domain.entity.Department;

import no.taxsee.addressbook.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by Marat Duisenov on 26.02.2017.
 */

public class DepartmentViewBinder extends TreeViewBinder<DepartmentViewBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {
        holder.imageArrow.setRotation(0);
        holder.imageArrow.setImageResource(R.drawable.ic_keyboard_arrow_right_black_18dp);
        int rotateDegree = node.isExpand() ? 90 : 0;
        holder.imageArrow.setRotation(rotateDegree);
        Department departmentsNode = (Department) node.getContent();
        holder.textName.setText(departmentsNode.getName());
        if (node.isLeaf())
            holder.imageArrow.setVisibility(View.INVISIBLE);
        else holder.imageArrow.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_department;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView imageArrow;
        private TextView textName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.imageArrow = (ImageView) rootView.findViewById(R.id.imageArrow);
            this.textName = (TextView) rootView.findViewById(R.id.textName);
        }

        public ImageView getImageArrow() {
            return imageArrow;
        }

        public TextView getTextName() {
            return textName;
        }
    }
}

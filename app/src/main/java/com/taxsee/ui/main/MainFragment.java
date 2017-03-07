package com.taxsee.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.taxsee.domain.entity.Department;
import com.taxsee.domain.entity.Employee;
import com.taxsee.presenter.modules.main.MainPresenter;
import com.taxsee.presenter.modules.main.MainView;
import com.taxsee.ui.base.BaseListFragment;
import com.taxsee.ui.main.employee.EmployeeActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import no.taxsee.addressbook.R;
import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

/**
 * Created by Marat Duisenov on 24.02.2017.
 */
public class MainFragment extends BaseListFragment implements MainView, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, TreeViewAdapter.OnTreeNodeListener{

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject MainPresenter mMainPresenter;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMainPresenter.attachView(this);
        fetchAll();
    }

    private void fetchAll() {
        mMainPresenter.fetchAll();
    }

    @Override
    public void onDestroyView() {
        mRecyclerView.setAdapter(null);
        super.onDestroyView();
    }

    @Override
    public void initUi() {
        initSwipeRefresh();
    }

    private void initRecyclerView(List<TreeNode> treeNodeList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        TreeViewAdapter mAdapter = new TreeViewAdapter(treeNodeList, Arrays.asList(new EmployeeViewBinder(), new DepartmentViewBinder()));
        // whether collapse child nodes when their parent node was close.
//        mAdapter.ifCollapseChildWhileCollapseParent(true);
        mAdapter.setOnTreeNodeListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initSwipeRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.accent,
                R.color.accent,
                R.color.accent,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showAll(TreeNode<Department> treeNode) {
        mNetworkProgressView.onSuccess();
        List<TreeNode> treeNodeList = new ArrayList<>();
        treeNodeList.add(treeNode);
        initRecyclerView(treeNodeList);
    }

    @Override
    public void showLoading() {
        mNetworkProgressView.retry();
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String error) {
        mNetworkProgressView.setOnRetryClickListener(this);
        mNetworkProgressView.onError("Произошла ошибка");
    }

    @Override
    public void onClick(View v) {
        fetchAll();
    }

    @Override
    public void onRefresh() {
        fetchAll();
    }

    @Override
    public boolean onClick(TreeNode treeNode, RecyclerView.ViewHolder viewHolder) {
        if (!treeNode.isLeaf()) {
            //Update and toggle the node.
            onToggle(!treeNode.isExpand(), viewHolder);
//                    if (!node.isExpand())
//                        mAdapter.collapseBrotherNode(node);
        }else{
            if(treeNode.getContent() instanceof Employee){
                startActivity(EmployeeActivity.getIntent(getActivity(), (Employee)treeNode.getContent()));
            }
        }
        return false;
    }

    @Override
    public void onToggle(boolean isExpand, RecyclerView.ViewHolder viewHolder) {
        DepartmentViewBinder.ViewHolder dirViewHolder = (DepartmentViewBinder.ViewHolder) viewHolder;
        final ImageView ivArrow = dirViewHolder.getImageArrow();
        int rotateDegree = isExpand ? 90 : -90;
        ivArrow.animate().rotationBy(rotateDegree)
                .start();
    }
}

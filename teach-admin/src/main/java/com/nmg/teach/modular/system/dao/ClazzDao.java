package com.nmg.teach.modular.system.dao;

import com.nmg.teach.core.node.ZTreeNode;

import java.util.List;

/**
 * Created by xiadingli on 2018/1/9.
 */
public interface ClazzDao {

    /**
     * 获取ztree的节点列表
     *
     * @return
     */
    List<ZTreeNode> tree();
}

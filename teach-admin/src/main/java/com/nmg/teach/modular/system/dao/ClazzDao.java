package com.nmg.teach.modular.system.dao;

import com.nmg.teach.core.node.ZTreeNode;
import org.apache.ibatis.annotations.Param;

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
    /**
     * 获取ztree的节点列表
     *
     * @return
     */
    List<ZTreeNode> treeByTeachId(@Param("teachId") Integer teachId);

    /**
     * 获取角色列表树
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> clazzTreeListByTeacherId(@Param("teacherId") Integer teacherId);

}

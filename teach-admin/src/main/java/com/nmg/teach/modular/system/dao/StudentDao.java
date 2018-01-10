package com.nmg.teach.modular.system.dao;

import com.nmg.teach.core.datascope.DataScope;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 管理员的dao
 *
 * @author xiadingli
 * @date 2017年2月12日 下午8:43:52
 */
public interface StudentDao {


    /**
     * 根据条件查询用户列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectStudents( @Param("name") String name, @Param("clazzId") Integer clazzId);


}

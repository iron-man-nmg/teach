package com.nmg.teach.common.persistence.dao;

import com.nmg.teach.common.persistence.model.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 学生表 Mapper 接口
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
public interface StudentMapper extends BaseMapper<Student> {
     int  insertAndGetId(Student student);
}
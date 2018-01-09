package com.nmg.teach.modular.system.service;

import com.nmg.teach.common.persistence.model.Student;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
public interface IStudentService extends IService<Student> {
    public int  insertAndGetId(Student student);
}

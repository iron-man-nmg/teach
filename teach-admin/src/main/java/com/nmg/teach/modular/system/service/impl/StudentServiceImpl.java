package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.Student;
import com.nmg.teach.common.persistence.dao.StudentMapper;
import com.nmg.teach.modular.system.service.IStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
	public int  insertAndGetId(Student student){
	    return baseMapper.insertAndGetId(student);
    }
}

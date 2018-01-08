package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.Teacher;
import com.nmg.teach.common.persistence.dao.TeacherMapper;
import com.nmg.teach.modular.system.service.ITeacherService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
	
}

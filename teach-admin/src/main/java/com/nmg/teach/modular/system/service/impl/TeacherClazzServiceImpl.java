package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.TeacherClazz;
import com.nmg.teach.common.persistence.dao.TeacherClazzMapper;
import com.nmg.teach.modular.system.service.ITeacherClazzService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 老师、班级关系表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class TeacherClazzServiceImpl extends ServiceImpl<TeacherClazzMapper, TeacherClazz> implements ITeacherClazzService {
	
}

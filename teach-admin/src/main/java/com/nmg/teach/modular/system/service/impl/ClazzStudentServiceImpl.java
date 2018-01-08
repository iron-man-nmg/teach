package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.ClazzStudent;
import com.nmg.teach.common.persistence.dao.ClazzStudentMapper;
import com.nmg.teach.modular.system.service.IClazzStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级、学生关系表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class ClazzStudentServiceImpl extends ServiceImpl<ClazzStudentMapper, ClazzStudent> implements IClazzStudentService {
	
}

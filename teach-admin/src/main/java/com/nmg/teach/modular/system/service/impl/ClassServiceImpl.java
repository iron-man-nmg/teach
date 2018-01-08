package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.Class;
import com.nmg.teach.common.persistence.dao.ClassMapper;
import com.nmg.teach.modular.system.service.IClassService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {
	
}

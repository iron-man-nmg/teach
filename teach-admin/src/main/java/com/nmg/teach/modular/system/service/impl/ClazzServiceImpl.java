package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.Clazz;
import com.nmg.teach.common.persistence.dao.ClazzMapper;
import com.nmg.teach.modular.system.service.IClazzService;
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
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements IClazzService {
	
}

package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.Timetables;
import com.nmg.teach.common.persistence.dao.TimetablesMapper;
import com.nmg.teach.modular.system.service.ITimetablesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class TimetablesServiceImpl extends ServiceImpl<TimetablesMapper, Timetables> implements ITimetablesService {
	
}

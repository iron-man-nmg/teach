package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.HistoryClazzStudent;
import com.nmg.teach.common.persistence.dao.HistoryClazzStudentMapper;
import com.nmg.teach.modular.system.service.IHistoryClazzStudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 班级、学生关系历史表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class HistoryClazzStudentServiceImpl extends ServiceImpl<HistoryClazzStudentMapper, HistoryClazzStudent> implements IHistoryClazzStudentService {
	
}

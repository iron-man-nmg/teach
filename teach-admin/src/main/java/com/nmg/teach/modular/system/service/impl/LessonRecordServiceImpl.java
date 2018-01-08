package com.nmg.teach.modular.system.service.impl;

import com.nmg.teach.common.persistence.model.LessonRecord;
import com.nmg.teach.common.persistence.dao.LessonRecordMapper;
import com.nmg.teach.modular.system.service.ILessonRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 上课记录表 服务实现类
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@Service
public class LessonRecordServiceImpl extends ServiceImpl<LessonRecordMapper, LessonRecord> implements ILessonRecordService {
	
}

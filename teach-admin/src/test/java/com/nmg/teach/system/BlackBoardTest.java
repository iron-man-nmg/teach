package com.nmg.teach.system;

import com.nmg.teach.base.BaseJunit;
import com.nmg.teach.modular.system.dao.NoticeDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * 首页通知展示测试
 *
 * @author xiadingli
 * @date 2017-05-21 15:02
 */
public class BlackBoardTest extends BaseJunit {

    @Autowired
    NoticeDao noticeDao;

    @Test
    public void blackBoardTest() {
        List<Map<String, Object>> notices = noticeDao.list(null);
        assertTrue(notices.size() > 0);
    }
}

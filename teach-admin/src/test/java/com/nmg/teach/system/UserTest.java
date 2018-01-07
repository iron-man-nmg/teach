package com.nmg.teach.system;

import com.nmg.teach.base.BaseJunit;
import com.nmg.teach.common.persistence.dao.UserMapper;
import com.nmg.teach.modular.system.dao.UserMgrDao;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * 用户测试
 *
 * @author xiadingli
 * @date 2017-04-27 17:05
 */
public class UserTest extends BaseJunit {

    @Resource
    UserMgrDao userMgrDao;

    @Resource
    UserMapper userMapper;

    @Test
    public void userTest() throws Exception {

    }

}

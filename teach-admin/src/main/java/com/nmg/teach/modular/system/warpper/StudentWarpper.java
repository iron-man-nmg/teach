package com.nmg.teach.modular.system.warpper;

import com.nmg.teach.common.constant.factory.ConstantFactory;
import com.nmg.teach.core.base.warpper.BaseControllerWarpper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的包装类
 *
 * @author xiadingli
 * @date 2017年2月13日 下午10:47:03
 */
public class StudentWarpper extends BaseControllerWarpper {

    public StudentWarpper(List<Map<String, Object>> list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        map.put("sexName", ConstantFactory.me().getSexName((Integer) map.get("sex")));
    }

}

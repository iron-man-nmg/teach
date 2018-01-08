package com.nmg.teach.modular.system.controller;

import com.nmg.teach.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.nmg.teach.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.nmg.teach.common.persistence.model.Class;
import com.nmg.teach.modular.system.service.IClassService;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:13:51
 */
@Controller
@RequestMapping("/class")
public class ClassController extends BaseController {

    private String PREFIX = "/system/class/";

    @Autowired
    private IClassService classService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "class.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/class_add")
    public String classAdd() {
        return PREFIX + "class_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/class_update/{classId}")
    public String classUpdate(@PathVariable Integer classId, Model model) {
        Class class = classService.selectById(classId);
        model.addAttribute("item",class);
        LogObjectHolder.me().set(class);
        return PREFIX + "class_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return classService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Class class) {
        classService.insert(class);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer classId) {
        classService.deleteById(classId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Class class) {
        classService.updateById(class);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{classId}")
    @ResponseBody
    public Object detail(@PathVariable("classId") Integer classId) {
        return classService.selectById(classId);
    }
}

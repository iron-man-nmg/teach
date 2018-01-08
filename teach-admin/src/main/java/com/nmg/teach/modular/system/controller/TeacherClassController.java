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
import com.nmg.teach.common.persistence.model.TeacherClass;
import com.nmg.teach.modular.system.service.ITeacherClassService;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:15:15
 */
@Controller
@RequestMapping("/teacherClass")
public class TeacherClassController extends BaseController {

    private String PREFIX = "/system/teacherClass/";

    @Autowired
    private ITeacherClassService teacherClassService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacherClass.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/teacherClass_add")
    public String teacherClassAdd() {
        return PREFIX + "teacherClass_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/teacherClass_update/{teacherClassId}")
    public String teacherClassUpdate(@PathVariable Integer teacherClassId, Model model) {
        TeacherClass teacherClass = teacherClassService.selectById(teacherClassId);
        model.addAttribute("item",teacherClass);
        LogObjectHolder.me().set(teacherClass);
        return PREFIX + "teacherClass_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return teacherClassService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TeacherClass teacherClass) {
        teacherClassService.insert(teacherClass);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teacherClassId) {
        teacherClassService.deleteById(teacherClassId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TeacherClass teacherClass) {
        teacherClassService.updateById(teacherClass);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{teacherClassId}")
    @ResponseBody
    public Object detail(@PathVariable("teacherClassId") Integer teacherClassId) {
        return teacherClassService.selectById(teacherClassId);
    }
}

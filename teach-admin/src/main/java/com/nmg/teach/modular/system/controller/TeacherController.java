package com.nmg.teach.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Strings;
import com.nmg.teach.common.persistence.model.Teacher;
import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.log.LogObjectHolder;
import com.nmg.teach.modular.system.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 22:09:04
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    private String PREFIX = "/system/teacher/";

    @Autowired
    private ITeacherService teacherService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacher.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/teacher_add")
    public String teacherAdd() {
        return PREFIX + "teacher_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/teacher_update/{teacherId}")
    public String teacherUpdate(@PathVariable Integer teacherId, Model model) {
        Teacher teacher = teacherService.selectById(teacherId);
        model.addAttribute("item", teacher);
        LogObjectHolder.me().set(teacher);
        return PREFIX + "teacher_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Wrapper<Teacher> wrapper = new EntityWrapper<>();
        if (!Strings.isNullOrEmpty(condition)) {
            wrapper.eq("name", condition);
        }
        return teacherService.selectList(wrapper);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teacher teacher) {
        teacherService.insert(teacher);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teacherId) {
        teacherService.deleteById(teacherId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Teacher teacher) {
        teacherService.updateById(teacher);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{teacherId}")
    @ResponseBody
    public Object detail(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.selectById(teacherId);
    }
}

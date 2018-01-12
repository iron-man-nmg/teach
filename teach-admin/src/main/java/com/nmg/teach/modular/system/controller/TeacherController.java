package com.nmg.teach.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Strings;
import com.nmg.teach.common.annotion.BussinessLog;
import com.nmg.teach.common.annotion.Permission;
import com.nmg.teach.common.constant.Const;
import com.nmg.teach.common.constant.dictmap.UserDict;
import com.nmg.teach.common.exception.BizExceptionEnum;
import com.nmg.teach.common.persistence.dao.TeacherMapper;
import com.nmg.teach.common.persistence.dao.UserMapper;
import com.nmg.teach.common.persistence.model.Teacher;
import com.nmg.teach.common.persistence.model.TeacherClazz;
import com.nmg.teach.common.persistence.model.User;
import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.base.tips.Tip;
import com.nmg.teach.core.db.Db;
import com.nmg.teach.core.exception.TeachException;
import com.nmg.teach.core.log.LogObjectHolder;
import com.nmg.teach.core.util.Convert;
import com.nmg.teach.core.util.ToolUtil;
import com.nmg.teach.modular.system.service.ITeacherClazzService;
import com.nmg.teach.modular.system.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ITeacherClazzService teacherClazzService;

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

    @Permission
    @RequestMapping("/teacher_assign/{teacherId}")
    public String roleAssign(@PathVariable Integer teacherId, Model model) {
        if (ToolUtil.isEmpty(teacherId)) {
            throw new TeachException(BizExceptionEnum.REQUEST_NULL);
        }
        Teacher teacher = (Teacher) Db.create(TeacherMapper.class).selectOneByCon("id", teacherId);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("teacherName", teacher.getName());
        return PREFIX + "teacher_clazzAssign.html";
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{teacherId}")
    @ResponseBody
    public Object detail(@PathVariable("teacherId") Integer teacherId) {
        return teacherService.selectById(teacherId);
    }


    /**
     * 分配角色
     */
    @RequestMapping("/setClazz")
    @BussinessLog(value = "分配角色", key = "teacherId,clazzIds", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip setClazz(@RequestParam("teacherId") Integer teacherId, @RequestParam("clazzIds") String clazzIds) {
        if (ToolUtil.isOneEmpty(teacherId, clazzIds)) {
            throw new TeachException(BizExceptionEnum.REQUEST_NULL);
        }
        //删除原来的班级
        Wrapper<TeacherClazz> wrapper = new EntityWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        teacherClazzService.delete(wrapper);
        List<TeacherClazz> list = new ArrayList<>();
        String[] strArray = Convert.toStrArray(",", clazzIds);
        for (String clazzId : strArray) {
            TeacherClazz teacherClazz = new TeacherClazz();
            teacherClazz.setClazzId(Integer.valueOf(clazzId));
            teacherClazz.setTeacherId(teacherId);
            list.add(teacherClazz);
        }
        teacherClazzService.insertBatch(list);
        //新增班级
        return SUCCESS_TIP;
    }
}

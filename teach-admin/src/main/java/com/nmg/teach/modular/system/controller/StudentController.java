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
import com.nmg.teach.common.persistence.model.Student;
import com.nmg.teach.modular.system.service.IStudentService;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:07:21
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {

    private String PREFIX = "/system/student/";

    @Autowired
    private IStudentService studentService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "student.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/student_add")
    public String studentAdd() {
        return PREFIX + "student_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/student_update/{studentId}")
    public String studentUpdate(@PathVariable Integer studentId, Model model) {
        Student student = studentService.selectById(studentId);
        model.addAttribute("item",student);
        LogObjectHolder.me().set(student);
        return PREFIX + "student_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return studentService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Student student) {
        studentService.insert(student);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer studentId) {
        studentService.deleteById(studentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Student student) {
        studentService.updateById(student);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{studentId}")
    @ResponseBody
    public Object detail(@PathVariable("studentId") Integer studentId) {
        return studentService.selectById(studentId);
    }
}

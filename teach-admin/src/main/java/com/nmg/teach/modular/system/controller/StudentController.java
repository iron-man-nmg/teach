package com.nmg.teach.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nmg.teach.common.exception.BizExceptionEnum;
import com.nmg.teach.common.persistence.model.*;
import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.exception.TeachException;
import com.nmg.teach.core.log.LogObjectHolder;
import com.nmg.teach.modular.system.dao.StudentDao;
import com.nmg.teach.modular.system.service.*;
import com.nmg.teach.modular.system.warpper.StudentWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private StudentDao studentDao;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClazzStudentService clazzStudentService;
    @Autowired
    private IPackPackageStudentService packPackageStudentService;
    @Autowired
    private IPackPackageService packPackageService;
    @Autowired
    private IClazzService clazzService;

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
        model.addAttribute("item", student);

        Wrapper<ClazzStudent> clazzStudentWrapper = new EntityWrapper<>();
        clazzStudentWrapper.eq("student_id", student.getId());
        ClazzStudent clazzStudent = clazzStudentService.selectOne(clazzStudentWrapper);
        Clazz clazz = clazzService.selectById(clazzStudent.getClazzId());
        model.addAttribute("clazz", clazz);

        Wrapper<PackPackageStudent> packStudentWrapper = new EntityWrapper<>();
        packStudentWrapper.eq("student_id", student.getId());
        PackPackageStudent packPackageStudent = packPackageStudentService.selectOne(packStudentWrapper);
        PackPackage packPackage = packPackageService.selectById(packPackageStudent.getPackageId());
        model.addAttribute("pack", packPackage);
        LogObjectHolder.me().set(student);
        return PREFIX + "student_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer clazzId, @RequestParam(required = false) String name) {
        if (clazzId != null && clazzId == 0) {
            clazzId = null;
        }
        List<Map<String, Object>> list = studentDao.selectStudents(name, clazzId);

        return new StudentWarpper(list).warp();
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/listByClazzId")
    @ResponseBody
    public Object listByClazzId(@RequestParam(required = false) Integer clazzId) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (clazzId != null) {
            list = studentDao.selectStudents("", clazzId);
        }
        return new StudentWarpper(list).warp();
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    @Transactional
    public Object add(Student student, @RequestParam Integer clazzId, @RequestParam Integer packId) {
        int result = studentService.insertAndGetId(student);
        if (result < 1 ) {
            throw new TeachException(BizExceptionEnum.ADD_ERROR);
        }
        ClazzStudent clazzStudent = new ClazzStudent();
        clazzStudent.setClazzId(clazzId);
        clazzStudent.setStudentId(student.getId());
        clazzStudentService.insert(clazzStudent);


        PackPackage packPackage = packPackageService.selectById(packId);

        PackPackageStudent packPackageStudent = new PackPackageStudent();
        packPackageStudent.setPackageId(packId);
        packPackageStudent.setStudentId(student.getId());
        packPackageStudent.setRemainingHour(packPackage.getClazzHour());
        packPackageStudentService.insert(packPackageStudent);
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

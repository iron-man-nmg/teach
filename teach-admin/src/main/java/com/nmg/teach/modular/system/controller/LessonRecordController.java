package com.nmg.teach.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.base.Strings;
import com.nmg.teach.common.exception.BizExceptionEnum;
import com.nmg.teach.common.persistence.dao.UserMapper;
import com.nmg.teach.common.persistence.model.LessonRecord;
import com.nmg.teach.common.persistence.model.PackPackageStudent;
import com.nmg.teach.common.persistence.model.Teacher;
import com.nmg.teach.common.persistence.model.User;
import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.exception.TeachException;
import com.nmg.teach.core.log.LogObjectHolder;
import com.nmg.teach.core.shiro.ShiroKit;
import com.nmg.teach.core.util.ToolUtil;
import com.nmg.teach.modular.system.dao.LessonRecordDao;
import com.nmg.teach.modular.system.service.ILessonRecordService;
import com.nmg.teach.modular.system.service.IPackPackageStudentService;
import com.nmg.teach.modular.system.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:51:42
 */
@Controller
@RequestMapping("/lessonRecord")
public class
LessonRecordController extends BaseController {

    private String PREFIX = "/system/lessonRecord/";

    @Autowired
    private ILessonRecordService lessonRecordService;
    @Autowired
    private LessonRecordDao lessonRecordDao;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IPackPackageStudentService packPackageStudentService;
    @Resource
    private UserMapper userMapper;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "lessonRecord.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/add")
    public String lessonRecordAdd(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new TeachException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userMapper.selectById(userId);
        Wrapper<Teacher> teacherWrapper = new EntityWrapper<>();
        teacherWrapper.eq("mobile", user.getPhone());
        //老师
        List<Teacher> teachers = teacherService.selectList(teacherWrapper);
        if (teachers == null || teachers.size() <= 0) {
            throw new TeachException(BizExceptionEnum.TEACH_NULL);
        }

        model.addAttribute("teacherId", teachers.get(0).getId());
        model.addAttribute("teacherName", teachers.get(0).getName());
        LogObjectHolder.me().set(model);
        return PREFIX + "lessonRecord_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/lessonRecord_update/{lessonRecordId}")
    public String lessonRecordUpdate(@PathVariable Integer lessonRecordId, Model model) {
        LessonRecord lessonRecord = lessonRecordService.selectById(lessonRecordId);
        model.addAttribute("item", lessonRecord);
        LogObjectHolder.me().set(lessonRecord);
        return PREFIX + "lessonRecord_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String name) {

        return lessonRecordDao.selectRecord(name);
    }

    /**
     * 上课签到
     */
    @RequestMapping(value = "/listStudent")
    @ResponseBody
    public Object listStudent(String clazzId) {

        return lessonRecordDao.selectClazzStudent(clazzId);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/signIn")
    @ResponseBody
    @Transactional
    public Object add(String studentIds) {
        if (Strings.isNullOrEmpty(studentIds)) {
            return SUCCESS_TIP;
        }
        String[] _stuentIds = studentIds.split(",");
        List<PackPackageStudent> list = new ArrayList<>();
        for (String studentId : _stuentIds) {
            Wrapper<PackPackageStudent> wrapper = new EntityWrapper<>();
            wrapper.eq("student_id", studentId);
            PackPackageStudent packPackageStudent = packPackageStudentService.selectOne(wrapper);
            if (packPackageStudent == null) {
                continue;
            }
            packPackageStudent.setRemainingHour(packPackageStudent.getRemainingHour() - 1);
            list.add(packPackageStudent);

        }
        packPackageStudentService.updateBatchById(list);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer lessonRecordId) {
        lessonRecordService.deleteById(lessonRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LessonRecord lessonRecord) {
        lessonRecordService.updateById(lessonRecord);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{lessonRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("lessonRecordId") Integer lessonRecordId) {
        return lessonRecordService.selectById(lessonRecordId);
    }
}

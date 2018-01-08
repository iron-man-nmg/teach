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
import com.nmg.teach.common.persistence.model.LessonRecord;
import com.nmg.teach.modular.system.service.ILessonRecordService;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:51:42
 */
@Controller
@RequestMapping("/lessonRecord")
public class LessonRecordController extends BaseController {

    private String PREFIX = "/system/lessonRecord/";

    @Autowired
    private ILessonRecordService lessonRecordService;

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
    @RequestMapping("/lessonRecord_add")
    public String lessonRecordAdd() {
        return PREFIX + "lessonRecord_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/lessonRecord_update/{lessonRecordId}")
    public String lessonRecordUpdate(@PathVariable Integer lessonRecordId, Model model) {
        LessonRecord lessonRecord = lessonRecordService.selectById(lessonRecordId);
        model.addAttribute("item",lessonRecord);
        LogObjectHolder.me().set(lessonRecord);
        return PREFIX + "lessonRecord_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return lessonRecordService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LessonRecord lessonRecord) {
        lessonRecordService.insert(lessonRecord);
        return super.SUCCESS_TIP;
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

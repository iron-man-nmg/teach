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
import com.nmg.teach.common.persistence.model.Timetables;
import com.nmg.teach.modular.system.service.ITimetablesService;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:14:19
 */
@Controller
@RequestMapping("/timetables")
public class TimetablesController extends BaseController {

    private String PREFIX = "/system/timetables/";

    @Autowired
    private ITimetablesService timetablesService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "timetables.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/timetables_add")
    public String timetablesAdd() {
        return PREFIX + "timetables_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/timetables_update/{timetablesId}")
    public String timetablesUpdate(@PathVariable Integer timetablesId, Model model) {
        Timetables timetables = timetablesService.selectById(timetablesId);
        model.addAttribute("item",timetables);
        LogObjectHolder.me().set(timetables);
        return PREFIX + "timetables_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return timetablesService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Timetables timetables) {
        timetablesService.insert(timetables);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer timetablesId) {
        timetablesService.deleteById(timetablesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Timetables timetables) {
        timetablesService.updateById(timetables);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{timetablesId}")
    @ResponseBody
    public Object detail(@PathVariable("timetablesId") Integer timetablesId) {
        return timetablesService.selectById(timetablesId);
    }
}

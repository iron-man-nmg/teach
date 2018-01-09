package com.nmg.teach.modular.system.controller;

import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.node.ZTreeNode;
import com.nmg.teach.modular.system.dao.ClazzDao;
import com.nmg.teach.modular.system.dao.DeptDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.nmg.teach.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.nmg.teach.common.persistence.model.Clazz;
import com.nmg.teach.modular.system.service.IClazzService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:51:12
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController extends BaseController {

    private String PREFIX = "/system/clazz/";

    @Autowired
    private IClazzService clazzService;
    @Resource
    ClazzDao clazzDao;
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "clazz.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/clazz_add")
    public String clazzAdd() {
        return PREFIX + "clazz_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/clazz_update/{clazzId}")
    public String clazzUpdate(@PathVariable Integer clazzId, Model model) {
        Clazz clazz = clazzService.selectById(clazzId);
        model.addAttribute("item",clazz);
        LogObjectHolder.me().set(clazz);
        return PREFIX + "clazz_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return clazzService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Clazz clazz) {
        clazzService.insert(clazz);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer clazzId) {
        clazzService.deleteById(clazzId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Clazz clazz) {
        clazzService.updateById(clazz);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{clazzId}")
    @ResponseBody
    public Object detail(@PathVariable("clazzId") Integer clazzId) {
        return clazzService.selectById(clazzId);
    }

    /**
     * 获取班级的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.clazzDao.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 获取班级的tree列表
     */
    @RequestMapping(value = "/treeList")
    @ResponseBody
    public List<ZTreeNode> treeList() {
        List<ZTreeNode> tree = this.clazzDao.tree();
        return tree;
    }

}

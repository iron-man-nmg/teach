package com.nmg.teach.modular.system.controller;

import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.node.ZTreeNode;
import com.nmg.teach.modular.system.dao.PackPackageDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.nmg.teach.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.nmg.teach.common.persistence.model.PackPackage;
import com.nmg.teach.modular.system.service.IPackPackageService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制器
 *
 * @author xiadingli
 * @Date 2018-01-09 20:54:11
 */
@Controller
@RequestMapping("/packPackage")
public class PackPackageController extends BaseController {

    private String PREFIX = "/system/packPackage/";
    @Resource
    private PackPackageDao packPackageDao;

    @Autowired
    private IPackPackageService packPackageService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "packPackage.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/packPackage_add")
    public String packPackageAdd() {
        return PREFIX + "packPackage_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/packPackage_update/{packPackageId}")
    public String packPackageUpdate(@PathVariable Integer packPackageId, Model model) {
        PackPackage packPackage = packPackageService.selectById(packPackageId);
        model.addAttribute("item",packPackage);
        LogObjectHolder.me().set(packPackage);
        return PREFIX + "packPackage_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return packPackageService.selectList(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PackPackage packPackage) {
        packPackageService.insert(packPackage);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer packPackageId) {
        packPackageService.deleteById(packPackageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PackPackage packPackage) {
        packPackageService.updateById(packPackage);
        return super.SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{packPackageId}")
    @ResponseBody
    public Object detail(@PathVariable("packPackageId") Integer packPackageId) {
        return packPackageService.selectById(packPackageId);
    }
     /**
     * 获取班级的tree列表
     */
    @RequestMapping(value = "/treeAll")
    @ResponseBody
    public List<ZTreeNode> treeAll() {
        List<ZTreeNode> tree = this.packPackageDao.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }
    /**
     * 获取班级的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.packPackageDao.tree();
        return tree;
    }
}

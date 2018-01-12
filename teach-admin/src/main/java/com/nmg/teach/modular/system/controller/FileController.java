package com.nmg.teach.modular.system.controller;

import com.nmg.teach.common.persistence.model.Teacher;
import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.util.POIUtil;
import com.nmg.teach.modular.system.service.impl.TeacherServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * 信息文件导入控制器
 *
 * @author xiadingli
 * @Date 2018-01-08 21:51:12
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {

    @Resource
    TeacherServiceImpl teacherService;

    private static final String[] fields = new String[]{"name", "mobile", "sex"};
    private String PREFIX = "/file/";
    private String SUFFIX = "xlsx";

    /**
     * 跳转到首页
     */
    @RequestMapping
    public String index() {
        return PREFIX + "index.html";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping(value = "/teacher/upload")
    @ResponseBody
    public String uploadXls(MultipartFile file, Model model) throws IOException {
        List<Teacher> teacherList = POIUtil.parseToList("xlsx", file.getInputStream(), Teacher.class, fields);

        if (CollectionUtils.isEmpty(teacherList)) {
            return "表格内容为空！";
        }

        for (Teacher teacher : teacherList) {
            try {
                teacherService.insert(teacher);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "success";
    }

    @RequestMapping(value = "/templet/download", method = RequestMethod.GET)
    public void download(int type, HttpServletResponse res, HttpServletRequest request) {
        String fileName = type == 1 ? "教师信息录入模板.xlsx" : "学生信息录入模板.xlsx";
        res.setContentType("utf-8");
        try {
            res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(res.getContentType());
        System.out.println(res.getHeader("Content-Disposition"));

        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            String filePath = request.getSession().getServletContext().getRealPath("/")
                    + "templet/" + fileName;
            System.out.println("文件路径为：" + filePath);
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("success");
    }
}
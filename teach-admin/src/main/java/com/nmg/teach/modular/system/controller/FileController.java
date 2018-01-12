package com.nmg.teach.modular.system.controller;

import com.nmg.teach.common.persistence.model.Student;
import com.nmg.teach.common.persistence.model.Teacher;
import com.nmg.teach.core.base.controller.BaseController;
import com.nmg.teach.core.util.POIUtil;
import com.nmg.teach.modular.system.service.impl.StudentServiceImpl;
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

    @Resource
    StudentServiceImpl studentService;

    private static final String[] teacherFields = new String[]{"name", "mobile", "sex"};

    private static final String[] studentFields = new String[]{"name", "contactPhone", "sex"};
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
     * 导入老师信息
     * @param file
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/teacher/upload")
    @ResponseBody
    public String uploadTeacher(MultipartFile file, Model model) throws IOException {
        List<Teacher> teacherList = POIUtil.parseToList("xlsx", file.getInputStream(), Teacher.class, teacherFields);

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

    /**
     * 导入学生信息
     * @param file
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/student/upload")
    @ResponseBody
    public String uploadStudent(MultipartFile file, Model model) throws IOException {
        List<Student> studentList = POIUtil.parseToList("xlsx", file.getInputStream(), Student.class, studentFields);

        if (CollectionUtils.isEmpty(studentList)) {
            return "表格内容为空！";
        }

        for (Student stu : studentList) {
            try {
                studentService.insert(stu);
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
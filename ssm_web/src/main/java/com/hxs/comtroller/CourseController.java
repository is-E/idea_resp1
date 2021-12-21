package com.hxs.comtroller;

import com.hxs.domain.Course;
import com.hxs.domain.Coursevo;
import com.hxs.domain.ResponseResult;
import com.hxs.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody Coursevo coursevo) {
        System.out.println(coursevo);
        List<Course> list = courseService.findCourseByCondition(coursevo);
        System.out.println(list);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1、判断file是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }
        //2、获取项目路径
        String realPath = request.getServletContext().getRealPath("/");
        String newPath = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3、获取文件名
        String name = file.getOriginalFilename();
        String newName = System.currentTimeMillis() + "_" + name;

        //4、文件上传
        String uploadPath = newPath + "\\upload";
        File filePath = new File(uploadPath, newName);
//        System.out.println("要上传的地址" + uploadPath);
//        System.out.println("上传后的文件路径" + filePath);
//
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:" + filePath);
        }

        //文件上传
        file.transferTo(filePath);

        Map<String, String> map = new HashMap<>();
        map.put("fileName", newName);
        map.put("filePath", "http://localhost:8080/upload/" + newName);

        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);
        return result;
    }


    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody Coursevo coursevo) {
        if (coursevo.getId() == 0) {
            courseService.saveCourseOrTeacher(coursevo);
        } else {
            courseService.updateCourseOrTeacher(coursevo);
        }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {
        Coursevo coursevo = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", coursevo);
        return result;
    }

    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id, @RequestParam Integer status) {
        courseService.updateCourseStatus(id, status);
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

}

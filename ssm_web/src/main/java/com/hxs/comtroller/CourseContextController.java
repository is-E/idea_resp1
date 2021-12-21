package com.hxs.comtroller;

import com.hxs.domain.Course;
import com.hxs.domain.CourseSection;
import com.hxs.domain.ResponseResult;
import com.hxs.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContextController {
    @Autowired
    private CourseContentService contentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(@RequestParam Integer courseId) {
        List<CourseSection> list = contentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId) {
        Course course = contentService.findCourseById(courseId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", course.getId());
        map.put("courseName", course.getCourseName());
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {
        if (courseSection.getId() == 0) {
            contentService.saveSection(courseSection);
        } else {
            contentService.updateSection(courseSection);
        }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam Integer id, @RequestParam Integer status) {
        contentService.updateSectionStatus(id, status);
        Map<String,Object> map =new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }

}

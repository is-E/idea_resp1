package com.hxs.service.impl;

import com.hxs.dao.CourseMapper;
import com.hxs.domain.Course;
import com.hxs.domain.Coursevo;
import com.hxs.domain.Teacher;
import com.hxs.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    //多条件查询
    @Override
    public List<Course> findCourseByCondition(Coursevo coursevo) {
        List<Course> list = courseMapper.findCourseByCondition(coursevo);
        return list;
    }

    //新增课程及讲师信息
    @Override
    public void saveCourseOrTeacher(Coursevo coursevo) {
        Course course = new Course();
        BeanUtils.copyProperties(coursevo, course);
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        course.setStatus(1);
        course.setIsDel(0);
        courseMapper.saveCourse(course);

        int courseId = course.getId();
        System.out.println(courseId);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(coursevo, teacher);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(courseId);
        courseMapper.saveTeacher(teacher);

    }

    //通过id查询课程及讲师信息
    @Override
    public Coursevo findCourseById(Integer id) {
        Coursevo coursevo = courseMapper.findCourseById(id);
        return coursevo;
    }

    //修改课程及讲师信息
    @Override
    public void updateCourseOrTeacher(Coursevo coursevo) {
        Course course = new Course();
        BeanUtils.copyProperties(coursevo, course);
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(coursevo, teacher);
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());
        courseMapper.updateTeacher(teacher);

    }

    //修改课程状态信息
    @Override
    public void updateCourseStatus(Integer id, Integer status) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        courseMapper.updateCourseStatus(course);
    }
}

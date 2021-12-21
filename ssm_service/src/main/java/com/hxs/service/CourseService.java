package com.hxs.service;

import com.hxs.domain.Course;
import com.hxs.domain.Coursevo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    //多条件查询
    public List<Course> findCourseByCondition(Coursevo coursevo);

    //新增课程及讲师信息
    public void saveCourseOrTeacher(Coursevo coursevo);

    //通过id查询课程及讲师信息
    public Coursevo findCourseById(Integer id);

    //修改课程及讲师信息
    public void updateCourseOrTeacher(Coursevo coursevo);

    //修改课程状态信息
    public void updateCourseStatus(Integer id,Integer status);

}

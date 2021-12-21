package com.hxs.dao;

import com.hxs.domain.Course;
import com.hxs.domain.Coursevo;
import com.hxs.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    //多条件查询
    public List<Course> findCourseByCondition(Coursevo coursevo);

    //保存课程信息
    public void saveCourse(Course course);

    //保存教师信息
    public void saveTeacher(Teacher teacher);

    //通过id查询课程及讲师信息
    public Coursevo findCourseById(@Param("id") Integer id);

    //更新课程信息
    public void updateCourse(Course course);

    //更新教师信息
    public void updateTeacher(Teacher teacher);

    //更新课程状态
    public void updateCourseStatus(Course course);
}


package com.hxs.dao;

import com.hxs.domain.Course;
import com.hxs.domain.CourseSection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseContentMapper {
    //根据课程id查询关联的章节信息及关联的课时信息
    public List<CourseSection> findSectionAndLessonByCourseId(@Param("id") Integer id);

    //回显章节对应的课程信息
    public Course findCourseById(@Param("courseId") Integer courseId);

    //保存章节
    public void saveSection(CourseSection courseSection);

    //更新章节信息
    public void updateSection(CourseSection courseSection);

    //更新章节状态
    public void updateSectionStatus(CourseSection courseSection);

}

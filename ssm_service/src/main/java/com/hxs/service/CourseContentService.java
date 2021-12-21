package com.hxs.service;

import com.hxs.dao.CourseContentMapper;
import com.hxs.domain.Course;
import com.hxs.domain.CourseSection;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseContentService {

    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    public Course findCourseById(Integer courseId);

    public void saveSection(CourseSection courseSection);


    public void updateSection(CourseSection courseSection);

    public void updateSectionStatus(Integer id, Integer status);
}

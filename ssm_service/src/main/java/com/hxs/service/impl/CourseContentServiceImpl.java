package com.hxs.service.impl;

import com.hxs.dao.CourseContentMapper;
import com.hxs.domain.Course;
import com.hxs.domain.CourseSection;
import com.hxs.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper contentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id) {
        return contentMapper.findSectionAndLessonByCourseId(id);
    }

    @Override
    public Course findCourseById(Integer courseId) {
        Course course = contentMapper.findCourseById(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseSection.setCreateTime(date);
        courseSection.setStatus(2);
        contentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setUpdateTime(date);
        contentMapper.updateSection(courseSection);

    }

    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        section.setUpdateTime(new Date());
        contentMapper.updateSectionStatus(section);
    }
}

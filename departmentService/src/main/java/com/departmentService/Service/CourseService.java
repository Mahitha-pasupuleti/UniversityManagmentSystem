package com.departmentService.Service;

import com.ums.common.Entity.Course;
import com.ums.common.Entity.Section;

public interface CourseService {
    void createCourse(Course course);
    void updateCourse(Course course);
    Course getCourseById(Long id);
    Course getCourseByCode(String courseCode);
    Course getCourseByName(String courseName);
    void addNewSection(Section section, String courseCode);
}

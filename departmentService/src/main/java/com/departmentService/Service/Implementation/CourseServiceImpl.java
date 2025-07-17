package com.departmentService.Service.Implementation;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.Repository.CourseRepository;
import com.departmentService.Service.CourseService;
import com.ums.common.Entity.Course;
import com.ums.common.Entity.Section;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(Course course) {
        System.out.println(course.toString());
        if ( courseRepository.existsByCourseCode(course.getCourseCode()) ) {
            throw new AlreadyExistsOrNotException("Course with given code already exists");
        }
        if ( courseRepository.existsByCourseName(course.getCourseName()) ) {
            throw new AlreadyExistsOrNotException("Course with given name already exists");
        }
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Course with given id doesn't exists!"));
    }

    @Override
    public Course getCourseByCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Course with given code doesn't exists!"));
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findByCourseName(courseName)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Course with given name doesn't exists!"));
    }

    @Override
    @Transactional
    public void addNewSection(Section section, String courseCode) {
        Course course = courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Course with given name doesn't exists!"));
        if ( !course.getListOfSections().contains(section) ) {
            section.setAssignedCourse(course);
            course.getListOfSections().add(section);
            courseRepository.save(course);
        }
    }
}

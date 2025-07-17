package com.departmentService.Repository;

import com.ums.common.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);
    Optional<Course> findByCourseName(String courseName);
    boolean existsByCourseCode(String courseCode);
    boolean existsByCourseName(String courseName);
}

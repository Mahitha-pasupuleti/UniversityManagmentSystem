package com.departmentService.Controller;

import com.departmentService.Mapper.Course.CourseMapper;
import com.departmentService.Request.Course.CourseRequest;
import com.departmentService.Request.Department.AssignRequest;
import com.departmentService.Service.CourseService;
import com.departmentService.Service.SectionService;
import com.ums.common.Entity.Course;
import com.ums.common.Entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/university/course")
public class courseController {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SectionService sectionService;

    @PreAuthorize("hasAnyRole('PRESIDENT', 'VICE_PRESIDENT', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createNewCourse(@RequestBody CourseRequest courseRequest) {
        Course course = courseMapper.toEntity(courseRequest);
        courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("New course created successfully!");
    }

    @PostMapping("/addSectionToCourse")
    public ResponseEntity<String> addSectionToCourse(@RequestBody AssignRequest assignRequest) {
        Section section = sectionService.getSectionByName(assignRequest.getTarget());
        courseService.addNewSection(section, assignRequest.getSource());
        return ResponseEntity.status(HttpStatus.OK).body("Section added to course successfully!");
    }
}

package com.departmentService.Controller;

import com.departmentService.Mapper.Department.DepartmentMapper;
import com.departmentService.Request.Department.AssignRequest;
import com.departmentService.Request.Department.DepartmentRequest;
import com.departmentService.Request.Department.UpdateDepartmentRequest;
import com.departmentService.Service.AdminService;
import com.departmentService.Service.CourseService;
import com.departmentService.Service.DepartmentService;
import com.ums.common.Entity.Course;
import com.ums.common.Entity.Department;
import com.ums.common.Entity.User.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/university/department")
public class departmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/test-post")
    public ResponseEntity<String> testPost() {
        return ResponseEntity.ok("POST works");
    }

    @PreAuthorize("hasRole('DEAN')")
    @PostMapping("/create")
    public ResponseEntity<String> createNewDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department department = departmentMapper.convertDeptRequestToDepartment(departmentRequest);
        departmentService.addNewDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body("New department created successfully!");
    }

    @PreAuthorize("hasRole('DEAN')")
    @PostMapping("/update")
    public ResponseEntity<String> updateDepartmentDetails(@RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
        Department department = departmentService.getDepartmentById(updateDepartmentRequest.getDepartmentId());
        if ( updateDepartmentRequest.getDepartmentPhoneNumber() != 0 ) {
            department.setDepartmentPhoneNumber( updateDepartmentRequest.getDepartmentPhoneNumber() );
        }
        if ( updateDepartmentRequest.getDepartmentDescription() != null ) {
            department.setDescription( updateDepartmentRequest.getDepartmentDescription() );
        }
        if ( updateDepartmentRequest.getDepartmentEmailId() != null ) {
            department.setDepartmentEmailId( updateDepartmentRequest.getDepartmentEmailId() );
        }
        departmentService.updateDepartmentDetails( department );
        return ResponseEntity.status(HttpStatus.OK).body("Department updated successfully!");
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(department);
    }

    @PreAuthorize("hasAnyRole('DEAN', 'PRESIDENT', 'VICE_PRESIDENT', 'ADMIN')")
    @PostMapping("/addAdminToDepartment")
    public ResponseEntity<String> addAdminToDepartment(@RequestBody AssignRequest assignRequest) {
        Admin admin = adminService.getAdminByUsername(assignRequest.getTarget());
        departmentService.addNewAdmin(admin, assignRequest.getSource());
        return ResponseEntity.status(HttpStatus.OK).body("New admin added successfully");
    }

    @PreAuthorize("hasAnyRole('PRESIDENT', 'VICE_PRESIDENT')")
    @PostMapping("/addCourseToDepartment")
    public ResponseEntity<String> addCourseToDepartment(@RequestBody AssignRequest assignRequest) {
        Course course = courseService.getCourseByCode(assignRequest.getTarget());
        departmentService.addNewCourse(course, assignRequest.getSource());
        return ResponseEntity.status(HttpStatus.OK).body("New course added successfully");
    }

}
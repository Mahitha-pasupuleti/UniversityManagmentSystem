package com.departmentService.Controller;

import com.departmentService.Mapper.User.StudentMapper;
import com.departmentService.Service.JwtService;
import com.departmentService.Service.StudentService;
import com.ums.common.Entity.Enum.UserRole;
import com.ums.common.Entity.User.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.departmentService.Request.User.StudentRequest;

@RestController
@RequestMapping("api/v1/university/student")
public class studentController {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/create")
    public ResponseEntity<String> createNewStudent(@RequestBody StudentRequest studentRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        String userRole = jwtService.extractRole(token).toUpperCase();
        String userEmail = jwtService.extractEmail(token);
        Student student = studentMapper.toEntity(studentRequest);
        student.setUsername(username);
        student.setUserRole(UserRole.valueOf(userRole.toUpperCase()));
        student.setEmailId(userEmail);
        studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Student created successfully");
    }

}

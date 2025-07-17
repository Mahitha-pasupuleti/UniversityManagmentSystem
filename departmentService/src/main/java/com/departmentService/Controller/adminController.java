package com.departmentService.Controller;

import com.departmentService.Mapper.User.AdminMapper;
import com.departmentService.Request.User.AdminRequest;
import com.departmentService.Service.AdminService;
import com.departmentService.Service.JwtService;
import com.ums.common.Entity.Enum.UserRole;
import com.ums.common.Entity.User.Admin;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/university/admin")
public class adminController {

    // add new president, vice-president, hr, dean, these all comes under admin they have diff roles
    // add new president, remove president, update president details

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AdminService adminService;

    // who can create admin, when only he with same username can create himself a registered president user
    @PreAuthorize("hasAnyRole('DEAN', 'PRESIDENT', 'VICE_PRESIDENT', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(@RequestBody AdminRequest adminRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        String userRole = jwtService.extractRole(token).toUpperCase();
        String userEmail = jwtService.extractEmail(token);
        Admin admin = adminMapper.toEntity(adminRequest);
        admin.setUsername(username);
        admin.setUserRole(UserRole.valueOf(userRole.toUpperCase()));
        admin.setEmailId(userEmail);
        adminService.addNewAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin Created Successfully " + admin.toString());
    }

    @GetMapping("/getAdminByName/{username}")
    public ResponseEntity<Admin> getDepartmentById(@PathVariable String username) {
        Admin admin = adminService.getAdminByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

}
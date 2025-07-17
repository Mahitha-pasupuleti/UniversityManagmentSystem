package com.departmentService.Service;

import com.departmentService.Repository.DepartmentRepository;
import com.ums.common.Entity.Course;
import com.ums.common.Entity.Department;
import com.ums.common.Entity.User.Admin;
import org.springframework.beans.factory.annotation.Autowired;

public interface DepartmentService {
    void addNewDepartment(Department department);
    void updateDepartmentDetails(Department department);
    Department getDepartmentById(Long id);
    void addNewAdmin(Admin president, String departmentCode);
    void addNewCourse(Course course, String departmentCode);
    Department getDepartmentByCode(String departmentCode);
}

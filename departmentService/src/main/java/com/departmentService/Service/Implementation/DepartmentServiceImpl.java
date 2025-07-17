package com.departmentService.Service.Implementation;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.Repository.AdminRepository;
import com.departmentService.Repository.DepartmentRepository;
import com.departmentService.Service.DepartmentService;
import com.ums.common.Entity.Course;
import com.ums.common.Entity.Department;
import com.ums.common.Entity.User.Admin;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void addNewDepartment(Department department) {
        Optional<Department> savedDepartment = departmentRepository.findByDepartmentCode(department.getDepartmentCode());
        if (savedDepartment.isEmpty()) {
            departmentRepository.save(department);
        } else {
            throw new AlreadyExistsOrNotException("Department already exists!");
        }
    }

    @Override
    public void updateDepartmentDetails(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findByDepartmentId(id)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Department doesnt exists!"));
    }

    @Override
    @Transactional
    public void addNewAdmin(Admin admin, String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Department doesn't exist!"));
        if (!department.getDepartAdmins().contains(admin)) {
            admin.setAssignedDepartment(department);
            department.getDepartAdmins().add(admin);
            departmentRepository.save(department); // Save Department
        }
    }

    @Override
    @Transactional
    public void addNewCourse(Course course, String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Department doesn't exist!"));
        if ( !department.getCoursesOfferedByDepartment().contains(course) ) {
            course.setAssignedDepartmentCourse(department);
            department.getCoursesOfferedByDepartment().add(course);
            departmentRepository.save(department);
        }
    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {
        return departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Department doesnt exists!"));
    }

}
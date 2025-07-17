package com.departmentService.Mapper.Department;

import com.departmentService.Request.Department.DepartmentRequest;
import com.ums.common.Entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department convertDeptRequestToDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setDepartmentName(departmentRequest.getDepartmentName());
        department.setDepartmentCode(departmentRequest.getDepartmentCode());
        department.setDepartmentEmailId(departmentRequest.getDepartmentEmailId());
        department.setDepartmentPhoneNumber(departmentRequest.getDepartmentPhoneNumber());
        department.setDescription(departmentRequest.getDepartmentDescription());
        return department;
    }

}

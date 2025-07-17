package com.departmentService.Request.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    private String departmentName;
    private String departmentCode;
    private String departmentDescription;
    private Long departmentPhoneNumber; // change
    private String departmentEmailId;
}

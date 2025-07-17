package com.departmentService.Request.Course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionRequest {
    private String sectionName;
    private String sectionCode;
    private Integer classCapacity;
}

package com.departmentService.Mapper.Course;

import com.departmentService.Request.Course.CourseRequest;
import com.ums.common.Entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toEntity(CourseRequest courseRequest);
    CourseRequest toDto(Course course);
}

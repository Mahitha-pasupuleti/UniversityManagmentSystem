package com.departmentService.Mapper.User;

import com.departmentService.Request.User.StudentRequest;
import com.ums.common.Entity.User.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentRequest studentRequest);
    StudentRequest toDto(Student student);
}

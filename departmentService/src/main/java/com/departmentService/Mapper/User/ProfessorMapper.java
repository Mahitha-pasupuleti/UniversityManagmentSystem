package com.departmentService.Mapper.User;

import com.departmentService.Request.User.ProfessorRequest;
import com.ums.common.Entity.User.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

    Professor toEntity(ProfessorRequest professorRequest);
    ProfessorRequest toDto(Professor professor);
}

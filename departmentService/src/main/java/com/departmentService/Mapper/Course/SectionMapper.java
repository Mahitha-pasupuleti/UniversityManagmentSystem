package com.departmentService.Mapper.Course;

import com.departmentService.Request.Course.SectionRequest;
import com.ums.common.Entity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SectionMapper {
    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    Section toEntity(SectionRequest sectionRequest);
    SectionRequest toDto(Section section);
}

package com.departmentService.Mapper.User;

import com.departmentService.Request.User.AdminRequest;
import com.ums.common.Entity.User.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    Admin toEntity(AdminRequest adminRequest);
    AdminRequest toDto(Admin admin);
}

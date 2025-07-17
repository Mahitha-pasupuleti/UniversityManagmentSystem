package com.departmentService.Service;

import com.ums.common.Entity.User.Admin;

public interface AdminService {
    void addNewAdmin(Admin admin);
    void updateAdmin(Admin admin);
    Admin getAdminByUsername(String username);
}

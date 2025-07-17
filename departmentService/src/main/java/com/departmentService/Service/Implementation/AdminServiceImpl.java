package com.departmentService.Service.Implementation;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.Repository.AdminRepository;
import com.departmentService.Service.AdminService;
import com.ums.common.Entity.User.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void addNewAdmin(Admin admin) {
        Optional<Admin> savedAdmin = adminRepository.findByUsername(admin.getUsername());
        if (savedAdmin.isEmpty()) {
            adminRepository.save(admin);
        } else {
            throw new AlreadyExistsOrNotException("Admin already created!");
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Admin with given username doesn't exists!"));
    }
}

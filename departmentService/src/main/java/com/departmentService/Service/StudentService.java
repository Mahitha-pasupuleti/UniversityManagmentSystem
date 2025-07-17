package com.departmentService.Service;

import com.ums.common.Entity.User.Student;

public interface StudentService {
    void createStudent(Student student);
    Student getStudentById(Long id);
    Student getStudentByName(String username);
    void updateStudent(Student student);
}

package com.departmentService.Service.Implementation;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.Repository.StudentRepository;
import com.departmentService.Service.StudentService;
import com.ums.common.Entity.User.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void createStudent(Student student) {
        Optional<Student> savedAdmin = studentRepository.findByUsername(student.getUsername());
        if (savedAdmin.isPresent() ) {
            throw new AlreadyExistsOrNotException("Student already created!");
        } else {
            studentRepository.save(student);
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Student with given id doesn't exists!"));
    }

    @Override
    public Student getStudentByName(String username) {
        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Student with given username doesn't exists!"));
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }
}

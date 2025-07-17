package com.departmentService.Service;

import com.ums.common.Entity.Section;
import com.ums.common.Entity.User.Professor;
import com.ums.common.Entity.User.Student;

public interface ProfessorService {
    void createProfessor(Professor professor);
    Professor getProfessorById(Long id);
    Professor getProfessorByName(String username);
    void updateAProfessor(Professor professor);
    void addNewSection(Section section, String username);
}

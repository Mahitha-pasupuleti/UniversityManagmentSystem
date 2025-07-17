package com.departmentService.Service.Implementation;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.Repository.ProfessorRepository;
import com.departmentService.Service.ProfessorService;
import com.ums.common.Entity.Section;
import com.ums.common.Entity.User.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void createProfessor(Professor professor) {
        Optional<Professor> savedAdmin = professorRepository.findByUsername(professor.getUsername());
        if (savedAdmin.isPresent() ) {
            throw new AlreadyExistsOrNotException("Professor already created!");
        } else {
            professorRepository.save(professor);
        }
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Professor with given id doesn't exists!"));
    }

    @Override
    public Professor getProfessorByName(String username) {
        return professorRepository.findByUsername(username)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Professor with given username doesn't exists!"));
    }

    @Override
    public void updateAProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public void addNewSection(Section section, String username) {
        Professor professor = getProfessorByName(username);
        if ( !professor.getAssignedSectionsToProfessor().contains(section) ) {
            section.setAssignedProfessor(professor);
            professor.getAssignedSectionsToProfessor().add(section);
            professorRepository.save(professor);
        }
    }
}

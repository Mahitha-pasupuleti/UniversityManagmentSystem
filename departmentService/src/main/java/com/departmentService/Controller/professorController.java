package com.departmentService.Controller;

import com.departmentService.Mapper.User.ProfessorMapper;
import com.departmentService.Repository.SectionRepository;
import com.departmentService.Request.Department.AssignRequest;
import com.departmentService.Request.User.ProfessorRequest;
import com.departmentService.Service.JwtService;
import com.departmentService.Service.ProfessorService;
import com.departmentService.Service.SectionService;
import com.ums.common.Entity.Enum.UserRole;
import com.ums.common.Entity.Section;
import com.ums.common.Entity.User.Professor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/university/professor")
public class professorController {

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping("/create")
    public ResponseEntity<String> createNewProfessor(@RequestBody ProfessorRequest professorRequest, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        String userRole = jwtService.extractRole(token).toUpperCase();
        String userEmail = jwtService.extractEmail(token);
        Professor professor = professorMapper.toEntity(professorRequest);
        professor.setUsername(username);
        professor.setUserRole(UserRole.valueOf(userRole.toUpperCase()));
        professor.setEmailId(userEmail);
        professorService.createProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Professor created successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PRESIDENT', 'VICE_PRESIDENT')")
    @PostMapping("/addSectionToProfessor")
    public ResponseEntity<String> addSectionToProfessor(@RequestBody AssignRequest assignRequest) {
        Section section = sectionService.getSectionByCode(assignRequest.getTarget());
        professorService.addNewSection(section, assignRequest.getSource());
        return ResponseEntity.status(HttpStatus.OK).body("New Section assigned to Professor successfully!");
    }

}

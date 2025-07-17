package com.departmentService.Controller;

import com.departmentService.Mapper.Course.SectionMapper;
import com.departmentService.Repository.SectionRepository;
import com.departmentService.Request.Course.SectionRequest;
import com.departmentService.Request.Department.AssignRequest;
import com.departmentService.Service.ProfessorService;
import com.departmentService.Service.SectionService;
import com.ums.common.Entity.Section;
import com.ums.common.Entity.User.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/university/section")
public class sectionController {

    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/create")
    public ResponseEntity<String> createNewSection(@RequestBody SectionRequest sectionRequest) {
        System.out.println(sectionRequest.toString());
        Section section = sectionMapper.toEntity(sectionRequest);
        System.out.println(section.toString());
        sectionService.createSection(section);
        return ResponseEntity.status(HttpStatus.CREATED).body("New section created successfully");
    }

}

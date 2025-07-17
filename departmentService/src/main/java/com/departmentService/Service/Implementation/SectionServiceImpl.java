package com.departmentService.Service.Implementation;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.Repository.SectionRepository;
import com.departmentService.Service.SectionService;
import com.ums.common.Entity.Section;
import com.ums.common.Entity.User.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public void createSection(Section section) {
        if ( !sectionRepository.existsBySectionName(section.getSectionName()) && !sectionRepository.existsBySectionCode(section.getSectionCode()) ) {
            sectionRepository.save(section);
        } else {
            throw new AlreadyExistsOrNotException("Section already created!");
        }
    }

    @Override
    public void updateSection(Section section) {

    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Section with given Id doesn't exists!"));
    }

    @Override
    public Section getSectionByName(String sectionName) {
        return sectionRepository.findBySectionName(sectionName)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Section with given name doesn't exists!"));
    }

    @Override
    public Section getSectionByCode(String sectionCode) {
        return sectionRepository.findBySectionCode(sectionCode)
                .orElseThrow(() -> new AlreadyExistsOrNotException("Section with given code doesn't exists!"));
    }

}

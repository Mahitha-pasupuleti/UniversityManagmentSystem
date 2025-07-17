package com.departmentService.Service;

import com.ums.common.Entity.Section;
import com.ums.common.Entity.User.Professor;

public interface SectionService {
    void createSection(Section section);
    void updateSection(Section section);
    Section getSectionById(Long id);
    Section getSectionByName(String sectionName);
    Section getSectionByCode(String sectionCode);
}

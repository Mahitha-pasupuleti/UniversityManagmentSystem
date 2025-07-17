package com.departmentService.Repository;

import com.ums.common.Entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findBySectionName(String sectionName);
    Optional<Section> findBySectionCode(String sectionCode);
    boolean existsBySectionName(String sectionName);
    boolean existsBySectionCode(String sectionCode);
}

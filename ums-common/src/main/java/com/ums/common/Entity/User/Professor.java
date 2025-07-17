package com.ums.common.Entity.User;

import com.ums.common.Entity.Course;
import com.ums.common.Entity.Section;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends User {
    @OneToMany(mappedBy = "assignedProfessor")
    private Set<Section> assignedSectionsToProfessor;
}

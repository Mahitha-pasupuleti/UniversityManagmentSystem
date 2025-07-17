package com.ums.common.Entity.User;

import com.ums.common.Entity.Course;
import com.ums.common.Entity.Section;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User{
    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Section> studentEnrolledSections;
}
package com.ums.common.Entity;

import com.ums.common.Entity.User.Professor;
import com.ums.common.Entity.User.Student;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "section")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    private String sectionName;
    private String sectionCode;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course assignedCourse;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "userId")
    private Professor assignedProfessor;

    private Integer classCapacity;

    @ManyToMany
    @JoinTable(name = "course_student",
                joinColumns = @JoinColumn(name = "section_id", referencedColumnName = "sectionId"),
                inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "userId")
    )
    private Set<Student> enrolledStudents = new HashSet<>();

    @Override
    public String toString() {
        return "Section{" +
                "classCapacity=" + classCapacity +
                ", createdAt=" + createdAt +
                ", sectionCode='" + sectionCode + '\'' +
                ", sectionId=" + sectionId +
                ", sectionName='" + sectionName + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(sectionId, section.sectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sectionId);
    }

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

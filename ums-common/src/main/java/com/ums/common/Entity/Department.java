package com.ums.common.Entity;

import com.ums.common.Entity.User.Admin;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(unique = true)
    private String departmentName; // can change by dean
    @Column(unique = true)
    private String departmentCode;
    private String description; // can change
    private Long departmentPhoneNumber; // can change
    private String departmentEmailId; // can change

    @OneToMany(mappedBy = "assignedDepartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Admin> departAdmins = new HashSet<>();

    @OneToMany(mappedBy = "assignedDepartmentCourse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> coursesOfferedByDepartment = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "Department{" +
                "updatedAt=" + updatedAt +
                ", description='" + description + '\'' +
                ", departmentPhoneNumber=" + departmentPhoneNumber +
                ", departmentName='" + departmentName + '\'' +
                ", departmentId=" + departmentId +
                ", departmentEmailId='" + departmentEmailId + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(departmentId);
    }

}

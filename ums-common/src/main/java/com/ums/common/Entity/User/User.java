package com.ums.common.Entity.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ums.common.Entity.Department;
import com.ums.common.Entity.Enum.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long userPhoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private String userAddress;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department assignedDepartment;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "User{" +
                "createdAt=" + createdAt +
                ", emailId='" + emailId + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", username='" + username + '\'' +
                ", userPhoneNumber=" + userPhoneNumber +
                ", userRole=" + userRole +
                ", userId=" + userId +
                ", updatedAt=" + updatedAt +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}

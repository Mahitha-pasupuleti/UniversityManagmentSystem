package com.ums.common.Entity.Enum;

public enum UserRole {
    STUDENT,             // Registers, enrolls in courses, views grades
    PROFESSOR,           // Manages course content, assigns grades
    ADMIN,               // Manages users, departments, system configs, // Approves enrollment, manages student records
    PRESIDENT,      // Manages department-level courses, professors
    VICE_PRESIDENT,
    DEAN
}

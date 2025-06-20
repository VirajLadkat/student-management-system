package com.StudentManagementSystem.repository;

import com.StudentManagementSystem.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    
    // Custom method to find all semesters for a given course
    List<Semester> findByCourseId(Long courseId);
    
}

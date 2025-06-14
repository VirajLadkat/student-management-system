package com.StudentManagementSystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.StudentManagementSystem.entity.Student;
@Repository
public interface Studentrepository extends JpaRepository<Student, Integer> {
	  // Search by first name or last name (case-insensitive)
	 Page<Student> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(String firstname, String lastname, Pageable pageable);
} 
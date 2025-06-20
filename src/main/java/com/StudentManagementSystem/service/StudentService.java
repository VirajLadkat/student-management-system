package com.StudentManagementSystem.service;

import java.util.List;

import com.StudentManagementSystem.entity.Student;
import org.springframework.data.domain.Page;


public interface StudentService {

	public List<Student> getAllStudents();
	
	public Student saveStudent(Student student);
	
	public Student getById(int id);
	
	public void deleteById(int id);
	
	Page<Student> findPaginatedAndSearched(String keyword, int page, int size);
	
	long getTotalCount();

}
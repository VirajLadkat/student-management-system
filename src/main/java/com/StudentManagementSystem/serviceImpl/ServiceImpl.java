package com.StudentManagementSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.StudentManagementSystem.entity.Student;
import com.StudentManagementSystem.service.StudentService;

@Service
public class ServiceImpl implements StudentService{

	@Autowired
	com.StudentManagementSystem.repository.Studentrepository Studentrepository;
	
	@Override
	public List<Student> getAllStudents() {

		List<Student> list=Studentrepository.findAll();
		
		return list;
	}
	
	@Override
	public Student saveStudent(Student student) {
		
		return Studentrepository.save(student);
	}

	@Override
	public Student getById(int id) {
		
		return Studentrepository.findById(id).get();
	}

	@Override
	public void deleteById(int id) {
		
		Studentrepository.deleteById(id);
	}

	@Override
	public Page<Student> findPaginatedAndSearched(String keyword, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
	    if (keyword != null && !keyword.isEmpty()) {
	        return Studentrepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCase(keyword, keyword, pageable);
	    }
	    return Studentrepository.findAll(pageable);
	}
	
	public long getTotalCount() {
	    return Studentrepository.count(); // For studentService
	}


}
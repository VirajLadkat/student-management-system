package com.StudentManagementSystem.serviceImpl;

import com.StudentManagementSystem.entity.Teacher;
import com.StudentManagementSystem.repository.TeacherRepository;
import com.StudentManagementSystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Page<Teacher> findPaginatedAndSearched(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").ascending());
        if (keyword != null && !keyword.trim().isEmpty()) {
            return teacherRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword, pageable);
        }
        return teacherRepository.findAll(pageable);
    }
}

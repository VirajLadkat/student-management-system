package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher saveTeacher(Teacher teacher);
    Teacher getById(int id);
    void deleteById(int id);
    Page<Teacher> findPaginatedAndSearched(String keyword, int page, int size);
}

package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Subject;
import java.util.List;

public interface SubjectService {
    Subject saveSubject(Subject subject);
    Subject getSubjectById(Long id);
    List<Subject> getSubjectsBySemesterId(Long semesterId);
    void deleteSubjectById(Long id);
}

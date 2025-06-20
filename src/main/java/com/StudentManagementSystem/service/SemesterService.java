package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Semester;
import java.util.List;

public interface SemesterService {
    Semester saveSemester(Semester semester);
    Semester getSemesterById(Long id);
    List<Semester> getSemestersByCourseId(Long courseId);
    void deleteSemesterById(Long id);
}

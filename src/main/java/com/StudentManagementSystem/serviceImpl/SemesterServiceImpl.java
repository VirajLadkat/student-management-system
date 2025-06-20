package com.StudentManagementSystem.serviceImpl;

import com.StudentManagementSystem.entity.Semester;
import com.StudentManagementSystem.repository.SemesterRepository;
import com.StudentManagementSystem.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Semester saveSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public Semester getSemesterById(Long id) {
        return semesterRepository.findById(id).orElse(null);
    }

    @Override
    public List<Semester> getSemestersByCourseId(Long courseId) {
        return semesterRepository.findByCourseId(courseId);
    }

    @Override
    public void deleteSemesterById(Long id) {
        semesterRepository.deleteById(id);
    }

}

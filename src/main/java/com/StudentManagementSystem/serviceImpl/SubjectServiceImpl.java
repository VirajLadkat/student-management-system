package com.StudentManagementSystem.serviceImpl;

import com.StudentManagementSystem.entity.Subject;
import com.StudentManagementSystem.repository.SubjectRepository;
import com.StudentManagementSystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Subject> getSubjectsBySemesterId(Long semesterId) {
        return subjectRepository.findBySemesterId(semesterId);
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectRepository.deleteById(id);
    }

}

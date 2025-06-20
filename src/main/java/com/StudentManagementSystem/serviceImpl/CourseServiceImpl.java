package com.StudentManagementSystem.serviceImpl;

import com.StudentManagementSystem.entity.Course;
import com.StudentManagementSystem.repository.CourseRepository;
import com.StudentManagementSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }


    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public long getTotalCount() {
        return courseRepository.count();
    }

	
}

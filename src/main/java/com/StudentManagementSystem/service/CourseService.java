package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Course;
import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);          
    Course saveCourse(Course course);
    void deleteCourseById(Long id);   
    long getTotalCount();
}


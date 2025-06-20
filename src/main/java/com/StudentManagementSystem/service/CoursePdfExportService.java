package com.StudentManagementSystem.service;

import com.StudentManagementSystem.entity.Course;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CoursePdfExportService {
    void exportCourseToPdf(Course course, HttpServletResponse response) throws IOException;
}

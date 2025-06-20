package com.StudentManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.StudentManagementSystem.service.CourseService;
import com.StudentManagementSystem.service.StudentService;
import com.StudentManagementSystem.service.TeacherService;

@Controller
public class DashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("studentCount", studentService.getTotalCount());
        model.addAttribute("teacherCount", teacherService.getTotalCount());
        model.addAttribute("courseCount", courseService.getTotalCount());
        return "dashboard";
    }

}


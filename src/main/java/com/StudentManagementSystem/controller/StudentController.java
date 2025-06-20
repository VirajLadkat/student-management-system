package com.StudentManagementSystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.StudentManagementSystem.entity.Student;
import com.StudentManagementSystem.service.StudentExcelExporter;
import com.StudentManagementSystem.service.StudentPdfExporter;
import com.StudentManagementSystem.service.StudentService;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/home")
    public String home() {
        return "home"; // returns home.html
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create-student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "create-student";
        }
        service.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable int id, Model model) {
        model.addAttribute("student", service.getById(id));
        return "edit_student";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(
            @PathVariable int id,
            @Valid @ModelAttribute("student") Student student,
            BindingResult result) {

        if (result.hasErrors()) {
            return "edit_student";
        }

        Student existingStudent = service.getById(id);
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());

        // Set the new fields
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setCourse(student.getCourse());
        existingStudent.setSemester(student.getSemester());
        existingStudent.setYear(student.getYear());

        service.saveStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteById(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String viewStudents(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            Model model) {

        Page<Student> studentPage = service.findPaginatedAndSearched(keyword, page, size);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "students";
    }

    @GetMapping("/students/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Student> students = service.getAllStudents();
        StudentExcelExporter exporter = new StudentExcelExporter(students);
        exporter.export(response);
    }

    @GetMapping("/students/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students.pdf";
        response.setHeader(headerKey, headerValue);

        List<Student> students = service.getAllStudents();
        StudentPdfExporter exporter = new StudentPdfExporter(students);
        exporter.export(response);
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }



    
}


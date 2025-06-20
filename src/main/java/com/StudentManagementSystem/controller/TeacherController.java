package com.StudentManagementSystem.controller;

import com.StudentManagementSystem.entity.Teacher;
import com.StudentManagementSystem.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.StudentManagementSystem.service.TeacherExcelExporter;
import com.StudentManagementSystem.service.TeacherPdfExporter;
import com.StudentManagementSystem.entity.Teacher;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Display list of teachers with optional search & pagination
    @GetMapping
    public String viewTeachers(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size,
                               Model model) {
        Page<Teacher> teacherPage = teacherService.findPaginatedAndSearched(keyword, page, size);
        model.addAttribute("teachers", teacherPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teacherPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "teachers";
    }

    // Show form to create a new teacher
    @GetMapping("/new")
    public String createTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "create-teacher";
    }

    // Handle form submission for creating a new teacher
    @PostMapping
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "create-teacher";
        }
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    // Show form to edit an existing teacher
    @GetMapping("/edit/{id}")
    public String editTeacherForm(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherService.getById(id));
        return "edit-teacher";
    }

    // Handle form submission to update teacher details
    @PostMapping("/edit/{id}")
    public String updateTeacher(@PathVariable int id,
                                @Valid @ModelAttribute("teacher") Teacher teacher,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "edit-teacher";
        }
        Teacher existing = teacherService.getById(id);
        existing.setUsername(teacher.getUsername());
        existing.setEmail(teacher.getEmail());
        existing.setDepartment(teacher.getDepartment());
        existing.setEmployeeId(teacher.getEmployeeId());
        teacherService.saveTeacher(existing);
        return "redirect:/teachers";
    }

    // Delete a teacher by ID
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable int id) {
        teacherService.deleteById(id);
        return "redirect:/teachers";
    }
    
    @GetMapping("/export/excel")
    public void exportTeachersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=teachers.xlsx");

        List<Teacher> teachers = teacherService.getAllTeachers();
        TeacherExcelExporter exporter = new TeacherExcelExporter(teachers);
        exporter.export(response);
    }

    @GetMapping("/export/pdf")
    public void exportTeachersToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=teachers.pdf");

        List<Teacher> teachers = teacherService.getAllTeachers();
        TeacherPdfExporter exporter = new TeacherPdfExporter(teachers);
        exporter.export(response);
    }


}

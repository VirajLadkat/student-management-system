package com.StudentManagementSystem.controller;

import com.StudentManagementSystem.entity.Course;
import com.StudentManagementSystem.entity.Semester;
import com.StudentManagementSystem.entity.Subject;
import com.StudentManagementSystem.service.CoursePdfExportService;
import com.StudentManagementSystem.service.CourseService;
import com.StudentManagementSystem.service.SemesterService;
import com.StudentManagementSystem.service.SubjectService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private SubjectService subjectService;

    // Show all courses
    @GetMapping
    public String listCourses(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("mode", "create");
        return "create-course";
    }


    // Handle create
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("mode", "edit");
        return "create-course"; // reuse same form
    }


    

    // Handle edit
    @PostMapping("/update/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute("course") Course updatedCourse) {
        Course existing = courseService.getCourseById(id);
        existing.setName(updatedCourse.getName());
        existing.setDuration(updatedCourse.getDuration());
        existing.setTotalFees(updatedCourse.getTotalFees());
        courseService.saveCourse(existing);
        return "redirect:/courses";
    }

    // Handle delete
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    // View full course structure: semesters & subjects
    @GetMapping("/view/{id}")
    public String viewCourseStructure(@PathVariable Long id, Model model) {
        Course course = courseService.getCourseById(id);
        List<Semester> semesters = semesterService.getSemestersByCourseId(id);
        model.addAttribute("course", course);
        model.addAttribute("semesters", semesters);
        return "view-course";
    }

    // --- SEMESTER operations (inline to course) ---

    @PostMapping("/{courseId}/semester/save")
    public String saveSemester(@PathVariable Long courseId, @ModelAttribute Semester semester) {
        Course course = courseService.getCourseById(courseId);
        semester.setCourse(course);
        semesterService.saveSemester(semester);
        return "redirect:/courses/view/" + courseId;
    }

    @GetMapping("/{courseId}/semester/delete/{semesterId}")
    public String deleteSemester(@PathVariable Long courseId, @PathVariable Long semesterId) {
        semesterService.deleteSemesterById(semesterId);
        return "redirect:/courses/view/" + courseId;
    }

    // --- SUBJECT operations (inline to semester) ---

    @PostMapping("/{courseId}/semester/{semesterId}/subject/save")
    public String saveSubject(@PathVariable Long courseId,
                              @PathVariable Long semesterId,
                              @ModelAttribute Subject subject) {
        Semester semester = semesterService.getSemesterById(semesterId);
        subject.setSemester(semester);
        subjectService.saveSubject(subject);
        return "redirect:/courses/view/" + courseId;
    }

    @GetMapping("/{courseId}/semester/{semesterId}/subject/delete/{subjectId}")
    public String deleteSubject(@PathVariable Long courseId,
                                @PathVariable Long semesterId,
                                @PathVariable Long subjectId) {
        subjectService.deleteSubjectById(subjectId);
        return "redirect:/courses/view/" + courseId;
    }
    
    @Autowired
    private CoursePdfExportService pdfExportService;



    @GetMapping("/{id}/export/pdf")
    public void exportCourseToPdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Course course = courseService.getCourseById(id);
        pdfExportService.exportCourseToPdf(course, response);
    }




    
}

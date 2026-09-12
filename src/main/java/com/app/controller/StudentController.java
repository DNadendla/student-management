package com.app.controller;

import com.app.entity.Student;
import com.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Display all students
    @GetMapping("/students")
    public String getStudents(Model model) {

        model.addAttribute("students",
                studentRepository.findAll());

        return "students";
    }


    // Show Add Student page
    @GetMapping("/students/new")
    public String showAddStudentForm(Model model) {

        model.addAttribute("student", new Student());

        return "add-student";
    }


    // Save student
    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute Student student) {

        studentRepository.save(student);

        return "redirect:/students";
    }


    // Show Edit Student page
    @GetMapping("/students/edit/{id}")
    public String showEditStudentForm(
            @PathVariable Long id,
            Model model) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid student ID: " + id));

        model.addAttribute("student", student);

        return "edit-student";
    }


    // Update student
    @PostMapping("/students/update")
    public String updateStudent(
            @ModelAttribute Student student) {

        studentRepository.save(student);

        return "redirect:/students";
    }


    // Delete student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentRepository.deleteById(id);

        return "redirect:/students";
    }
}
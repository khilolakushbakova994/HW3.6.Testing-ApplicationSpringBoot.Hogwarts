package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.controller;


import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Student;
import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.service.AvatarService;
import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController (StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;}



    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> find(@PathVariable Long id) {
        Student student = studentService.find(id);
        if (student == null) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(student);

    }

    @PutMapping
    public ResponseEntity<Student> changeStudentInfo(@RequestBody Student student) {
        Student changeStudent = studentService.changeStudentInfo(student);
        if (changeStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(changeStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/all-students")
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/search-age")
    public Collection<Student> findStudentByAge(@PathVariable Integer age) {
        return studentService.findStudentByAge(age);
    }

    @GetMapping("/sort-age")
    public Collection<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);

    }

}

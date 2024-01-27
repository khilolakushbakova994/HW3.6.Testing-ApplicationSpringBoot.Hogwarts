package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.controller;



import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Faculty;
import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Student;
import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {return facultyService.createFaculty(faculty);

    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> findFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> changeFacultyInfo(@RequestBody Faculty faculty) {
        Faculty changedFaculty = facultyService.changeFacultyInfo(faculty);
        if (changedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(changedFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity removeFaculty(@PathVariable Long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sort-faculty")
    public ResponseEntity<Collection<Faculty>> findFacultyByColorOrName(@RequestParam(required = false) String color,
                                                                        @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {
            ResponseEntity.ok(facultyService.findFacultyByColor(color));
        }
        if (name != null && !name.isBlank()) {
            ResponseEntity.ok(facultyService.findFacultyByName(name));
        }
        return ResponseEntity.ok(facultyService.findAllFaculties());
    }

    @GetMapping("/all-faculties")
    public ResponseEntity<Collection<Faculty>> findAllFaculties() {
        return ResponseEntity.ok( facultyService.findAllFaculties());
    }

    //@GetMapping("{id-faculty}")
    //public ResponseEntity<Collection<Student>> findAllByFaculty (@PathVariable Long id) {
    //    return ResponseEntity.ok(facultyService.findStudentByFaculty(id));
   // }

}

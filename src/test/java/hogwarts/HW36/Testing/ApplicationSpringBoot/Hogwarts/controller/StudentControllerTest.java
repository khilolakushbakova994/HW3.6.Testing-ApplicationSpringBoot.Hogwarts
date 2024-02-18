package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.controller;

import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
//    @Autowired
//    private FacultyRepository facultyRepository;
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @BeforeEach
//    private void clear(){
//        facultyRepository.deleteAll();
//        studentRepository.deleteAll();
//    }
    //надо весь этот код добавить , чтоб вычищал БД
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student(63L, "Frank", 25, null);
        var result = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class);
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Frank");

    }

    @Test
    public void testFindStudent() throws Exception {
        Student student = new Student();
        student.setId(2L);

        String url = "http://localhost:" + port + "/student/" + student.getId();
        Student student1 = restTemplate.getForObject(url, Student.class);
        assertThat(student1).isNotNull();
        assertThat(student1.getId()).isEqualTo(2L);

    }

    @Test
    public void testRemoveStudent() throws Exception {
        Student student = new Student();
        student.setName("Frank");
        student.setAge(25);
        student.setId(85L);
        student.setFaculty(null);

        restTemplate.delete("http://localhost:" + port + "/student/", student.getId());

         Student delitedStudent =restTemplate.getForObject(
                 "http://localhost:" + port + "/student/" + student.getId(), Student.class);

        assertThat(delitedStudent.getId()).isNull();
    }

    @Test
    public void testChangeStudentInfo() throws Exception {
        Student student = new Student();
        student.setName("Maria");
        student.setAge(25);
        Long id = this.restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class).getId();

        //Student student2 = new Student();
        student.setName("Igor");
        student.setAge(20);
        student.setId(id);

        restTemplate.put("http://localhost:" + port + "/student", student);

        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, String.class)).
                contains("Igor");
    }

    @Test
    public void findStudentByAge() throws Exception {
        Student student = new Student();
        student.setName("Maria");
        student.setId(2L);
        student.setAge(18);
        student.setFaculty(null);
        this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class);

        var url = "http://localhost:" + port + "/student/search-age/18";
        Collection<Student> student1 = restTemplate.getForObject(url, Collection.class);
        assertThat(student1).isNotNull();
    }


    @Test
    public void findByAgeBetween() throws Exception {
        Student student = new Student();
        student.setId(2L);
        student.setAge(18);
        this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class);


        var url = "http://localhost:" + port + "/student/sort-age?min=15&max=27";
        Collection<Student> student1 = restTemplate.getForObject(url, Collection.class);
        assertThat(student1).isNotNull();


    }

    @Test
    public void getAllStudents() throws Exception {
        Student student = new Student();
        student.setId(2L);
        var url = "http://localhost:" + port + "/student/all-students";
        Collection<Student> student1 = restTemplate.getForObject(url, Collection.class);
        assertThat(student1).isNotNull();

    }

}
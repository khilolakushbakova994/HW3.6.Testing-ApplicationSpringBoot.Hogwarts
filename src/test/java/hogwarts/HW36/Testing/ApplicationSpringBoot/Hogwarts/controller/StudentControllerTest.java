package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.controller;

import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads () throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setName("Frank");
        student.setAge(25);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student,  String.class))
                .isNotNull();

    }

    @Test
     public void testFindStudent () throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class));

    }

}
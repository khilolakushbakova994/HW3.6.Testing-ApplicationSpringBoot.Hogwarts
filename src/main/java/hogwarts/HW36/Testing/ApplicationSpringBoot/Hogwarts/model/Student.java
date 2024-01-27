package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="students")
@Entity


public class Student {

    @Id
    @GeneratedValue
    private  Long id;
    @Column
    private  String name;
    @Column
    private int age;


    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @Getter
    private Faculty faculty;
}

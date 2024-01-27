package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.repository;



import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultyByColorOrNameContainsIgnoreCase  (String color, String name);
    Collection<Faculty>findFacultyByNameIgnoreCase (String name);
    Collection<Faculty>findFacultyByColorIgnoreCase (String color);


}


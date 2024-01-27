package hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.repository;


import hogwarts.HW36.Testing.ApplicationSpringBoot.Hogwarts.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findAvatarById (Long studentId);
}
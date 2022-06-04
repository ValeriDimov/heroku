package bg.softuni.Mine_mobilelele.repository;

import bg.softuni.Mine_mobilelele.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

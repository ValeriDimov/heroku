package bg.softuni.Mine_mobilelele.repository;

import bg.softuni.Mine_mobilelele.models.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}

package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.UserEntity;
import bg.softuni.battleships.models.dtos.UserRegisterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findAllByUsername(String username);

    Optional<UserEntity> findAllByEmail(String email);
}

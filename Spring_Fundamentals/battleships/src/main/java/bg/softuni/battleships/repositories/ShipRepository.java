package bg.softuni.battleships.repositories;

import bg.softuni.battleships.models.ShipEntity;
import bg.softuni.battleships.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {

    Optional<ShipEntity> findByName(String name);

    List<ShipEntity> findAllByUser(UserEntity user);

    List<ShipEntity> findAllByUserIsNot(UserEntity user);
}

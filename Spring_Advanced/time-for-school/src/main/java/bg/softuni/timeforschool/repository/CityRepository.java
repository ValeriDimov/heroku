package bg.softuni.timeforschool.repository;

import bg.softuni.timeforschool.model.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
}

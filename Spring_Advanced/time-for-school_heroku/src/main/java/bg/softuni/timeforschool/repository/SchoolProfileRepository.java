package bg.softuni.timeforschool.repository;

import bg.softuni.timeforschool.model.entity.SchoolProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolProfileRepository extends JpaRepository<SchoolProfileEntity, Long> {
}

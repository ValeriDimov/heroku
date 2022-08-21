package bg.softuni.timeforschool.repository;

import bg.softuni.timeforschool.model.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long>, JpaSpecificationExecutor<SchoolEntity> {
}

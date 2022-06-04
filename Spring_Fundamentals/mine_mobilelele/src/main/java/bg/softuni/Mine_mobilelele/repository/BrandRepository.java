package bg.softuni.Mine_mobilelele.repository;

import bg.softuni.Mine_mobilelele.models.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

}

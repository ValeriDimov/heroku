package bg.softuni.Mine_mobilelele.repository;

import bg.softuni.Mine_mobilelele.models.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {

}

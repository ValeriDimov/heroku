package bg.softuni.Mine_mobilelele.repository;

import bg.softuni.Mine_mobilelele.models.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

}

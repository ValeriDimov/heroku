package bg.softuni.timeforschool.repository;

import bg.softuni.timeforschool.model.entity.DeletedOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedOfferRepository extends JpaRepository<DeletedOfferEntity, Long> {
}

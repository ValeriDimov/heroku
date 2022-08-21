package bg.softuni.timeforschool.repository;

import bg.softuni.timeforschool.model.dto.SearchOfferDTO;
import bg.softuni.timeforschool.model.entity.OfferEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Locale;

public class OfferSpecification implements Specification<OfferEntity> {

  private final SearchOfferDTO searchOfferDTO;

  public OfferSpecification(SearchOfferDTO searchOfferDTO) {
    this.searchOfferDTO = searchOfferDTO;
  }

  @Override
  public Predicate toPredicate(Root<OfferEntity> root,
                               CriteriaQuery<?> query,
                               CriteriaBuilder cb) {
    Predicate p = cb.conjunction();

    if (searchOfferDTO.getCourse() != null && !searchOfferDTO.getCourse().isEmpty()) {
      p.getExpressions().add(
              cb.and(cb.like(cb.lower(root.get("course")),
                      "%" + searchOfferDTO.getCourse().toLowerCase() + "%")));
    }

    if (searchOfferDTO.getSeller() != null && !searchOfferDTO.getSeller().isEmpty()) {
      p.getExpressions().add(
              cb.and(cb.like(cb.lower(root.join("seller").get("name")),
                      "%" + searchOfferDTO.getSeller().toLowerCase() + "%")));
    }

    if (searchOfferDTO.getDescription() != null && !searchOfferDTO.getDescription().isEmpty()) {
      p.getExpressions().add(
          cb.and(cb.like(cb.lower(root.get("description")),
                  "%" + searchOfferDTO.getDescription().toLowerCase() + "%")));
    }


    return p;
  }
}

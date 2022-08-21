package bg.softuni.timeforschool.repository;

import bg.softuni.timeforschool.model.dto.SearchOfferDTO;
import bg.softuni.timeforschool.model.dto.SearchSchoolDTO;
import bg.softuni.timeforschool.model.entity.OfferEntity;
import bg.softuni.timeforschool.model.entity.SchoolEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class SchoolSpecification implements Specification<SchoolEntity> {

    private final SearchSchoolDTO searchSchoolDTO;

    public SchoolSpecification(SearchSchoolDTO searchSchoolDTO) {

        this.searchSchoolDTO = searchSchoolDTO;
    }

    @Override
    public Predicate toPredicate(Root<SchoolEntity> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder cb) {
        Predicate p = cb.conjunction();

        if (searchSchoolDTO.getName() != null && !searchSchoolDTO.getName().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.like(cb.lower(root.get("name")),
                            "%" + searchSchoolDTO.getName().toLowerCase() + "%")));
        }

        if (searchSchoolDTO.getDistrictName() != null && !searchSchoolDTO.getDistrictName().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.like(cb.lower(root.join("district").get("name")),
                            "%" + searchSchoolDTO.getDistrictName().toLowerCase() + "%")));
        }

        if (searchSchoolDTO.getCityName() != null && !searchSchoolDTO.getCityName().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.like(cb.lower(root.join("city").get("name")),
                            "%" + searchSchoolDTO.getCityName().toLowerCase() + "%")));
        }


        return p;
    }
}

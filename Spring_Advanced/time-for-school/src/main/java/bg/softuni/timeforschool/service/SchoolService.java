package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.SchoolDetailDTO;
import bg.softuni.timeforschool.model.dto.SearchSchoolDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolService {
    public Page getAllSchools(Pageable pageable) {
        return null;

//        TODO:implement
    }

    public Object searchOffer(SearchSchoolDTO searchSchoolDTO) {
        return null;
    }

    public Optional<SchoolDetailDTO> findSchoolByID(Long id) {
        return Optional.empty();
    }
}

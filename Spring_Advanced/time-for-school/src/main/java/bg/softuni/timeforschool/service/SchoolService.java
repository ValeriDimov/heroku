package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.OfferDetailDTO;
import bg.softuni.timeforschool.model.dto.SchoolDetailDTO;
import bg.softuni.timeforschool.model.dto.SearchSchoolDTO;
import bg.softuni.timeforschool.repository.OfferSpecification;
import bg.softuni.timeforschool.repository.SchoolRepository;
import bg.softuni.timeforschool.repository.SchoolSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final ModelMapper modelMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(ModelMapper modelMapper, SchoolRepository schoolRepository) {
        this.modelMapper = modelMapper;
        this.schoolRepository = schoolRepository;
    }

    public Page<SchoolDetailDTO> getAllSchools(Pageable pageable) {
        return schoolRepository.
                findAll(pageable).
                map(offer -> modelMapper.map(offer, SchoolDetailDTO.class));
    }

    public List<SchoolDetailDTO> searchSchool(SearchSchoolDTO searchSchoolDTO) {
        List<SchoolDetailDTO> schoolDetailDTOS = new ArrayList<>();
        List<SchoolDetailDTO> newDTOs = new ArrayList<>();

        schoolDetailDTOS = this.schoolRepository.findAll(new SchoolSpecification(searchSchoolDTO)).
                stream().map(school -> modelMapper.map(school, SchoolDetailDTO.class)).
                toList();

        if (searchSchoolDTO.getProfile() != null && !searchSchoolDTO.getProfile().isEmpty()) {
            String profile = searchSchoolDTO.getProfile();

            for (SchoolDetailDTO schoolDetailDTO : schoolDetailDTOS) {
                List<String> schoolProfiles = schoolDetailDTO.getSchoolProfiles();

                for (String schoolProfile : schoolProfiles) {
                    if (schoolProfile.equals(profile)) {
                        newDTOs.add(schoolDetailDTO);
                    }
                }
            }
        }

        if (!newDTOs.isEmpty()) {
            return newDTOs;
        }

        return schoolDetailDTOS;
    }

    public Optional<SchoolDetailDTO> findSchoolByID(Long id) {
        return schoolRepository.
                findById(id).
                map(offer -> modelMapper.map(offer, SchoolDetailDTO.class));
    }
}

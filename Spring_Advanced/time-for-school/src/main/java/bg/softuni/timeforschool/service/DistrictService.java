package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.DistrictDTO;
import bg.softuni.timeforschool.repository.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;

    public DistrictService(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }


    public List<DistrictDTO> getAllDistricts() {
        return this.districtRepository.
                findAll().
                stream().map(district -> modelMapper.map(district, DistrictDTO.class)).
                toList();

    }
}

package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.CityDTO;
import bg.softuni.timeforschool.model.dto.DistrictDTO;
import bg.softuni.timeforschool.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    public CityService(ModelMapper modelMapper, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    public List<CityDTO> getAllCities() {
        return this.cityRepository.
                findAll().
                stream().map(city -> modelMapper.map(city, CityDTO.class)).
                toList();
    }
}

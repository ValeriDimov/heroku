package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.CityDTO;
import bg.softuni.timeforschool.model.dto.DistrictDTO;
import bg.softuni.timeforschool.model.dto.SchoolDetailDTO;
import bg.softuni.timeforschool.model.dto.SearchSchoolDTO;
import bg.softuni.timeforschool.model.entity.*;
import bg.softuni.timeforschool.model.enums.SchoolProfileEnum;
import bg.softuni.timeforschool.repository.CityRepository;
import bg.softuni.timeforschool.repository.DistrictRepository;
import bg.softuni.timeforschool.repository.SchoolProfileRepository;
import bg.softuni.timeforschool.repository.SchoolRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SchoolServiceTest {

    @Mock
    private SchoolService schoolService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private DistrictRepository districtRepository;

    @Mock
    private SchoolProfileRepository schoolProfileRepository;

    @Mock
    private SchoolRepository schoolRepository;


    @Test
    void testLoadOfferByUserExists() {
        CityEntity testCity = createTestCity1();
        DistrictEntity testDistrict = createTestDistrict1(testCity);
        createTestSchoolProfiles1();
        SchoolEntity testSchool = createTestSchool1(testCity, testDistrict);

        SearchSchoolDTO searchSchoolDTO = new SearchSchoolDTO()
                .setName("78 СУ Христо Смирненски")
                .setDistrictName("Район Витоша")
                .setSchoolUrl("http://www.78sou.net/")
                .setEmail("sou_78@abv.bg")
                .setAddress("ул. Царибродска № 5")
                .setDirector("Димитрийка Герасимова")
                .setTelephone("Директор: 997-70-94")
                .setCityName("Sofia")
                .setProfile("FRENCH_LANGUAGE");

        List<SchoolDetailDTO> schoolDetailDTOS = schoolService.searchSchool(searchSchoolDTO);

        Assertions.assertEquals(testSchool.getName(), searchSchoolDTO.getName());
        Assertions.assertEquals(testSchool.getSchoolUrl(), searchSchoolDTO.getSchoolUrl());
        Assertions.assertEquals(testSchool.getAddress(), searchSchoolDTO.getAddress());
        Assertions.assertEquals(testSchool.getCity().getName(), searchSchoolDTO.getCityName());
        Assertions.assertEquals(testSchool.getDirector(), searchSchoolDTO.getDirector());
        Assertions.assertEquals(testSchool.getDistrict().getName(), searchSchoolDTO.getDistrictName());
        Assertions.assertEquals(testSchool.getEmail(), searchSchoolDTO.getEmail());
        Assertions.assertEquals(testSchool.getTelephone(), searchSchoolDTO.getTelephone());
    }

    private CityEntity createTestCity1() {
        CityEntity city = (CityEntity) new CityEntity().
                setName("Sofia")
                .setId(1L);

        when(cityRepository.findById(city.getId())).
                thenReturn(Optional.of(city));

        CityEntity newCity = cityRepository.findById(city.getId()).orElseThrow();

        return newCity;
    }

    private DistrictEntity createTestDistrict1(CityEntity cityEntity) {
        DistrictEntity district = (DistrictEntity) new DistrictEntity().
                setName("Район Витоша").
                setCity(cityEntity).
                setId(1L);

        when(districtRepository.findById(district.getId())).
                thenReturn(Optional.of(district));

        DistrictEntity newDistrict = districtRepository.findById(district.getId()).orElseThrow();

        return newDistrict;
    }

    private void createTestSchoolProfiles1() {
        SchoolProfileEntity schoolProfile1 = (SchoolProfileEntity) new SchoolProfileEntity()
                .setProfile(SchoolProfileEnum.ECONOMICS)
                .setId(1L);

        SchoolProfileEntity schoolProfile2 = (SchoolProfileEntity) new SchoolProfileEntity()
                .setProfile(SchoolProfileEnum.FRENCH_LANGUAGE)
                .setId(2L);

        schoolProfileRepository.save(schoolProfile1);
        schoolProfileRepository.save(schoolProfile2);

        when(schoolProfileRepository.findById(1L)).
                thenReturn(Optional.of(schoolProfile1));
        when(schoolProfileRepository.findById(2L)).
                thenReturn(Optional.of(schoolProfile2));

        SchoolProfileEntity firstProfile = schoolProfileRepository.findById(schoolProfile1.getId()).orElseThrow();
        SchoolProfileEntity secondProfile = schoolProfileRepository.findById(schoolProfile2.getId()).orElseThrow();


    }

    private SchoolEntity createTestSchool1(CityEntity city,
                                           DistrictEntity district) {

        createTestSchoolProfiles1();

        SchoolEntity school = (SchoolEntity) new SchoolEntity()
                .setName("78 СУ Христо Смирненски")
                .setSchoolProfiles(schoolProfileRepository.findAll())
                .setSchoolUrl("http://www.78sou.net/")
                .setCity(city)
                .setEmail("sou_78@abv.bg")
                .setAddress("ул. Царибродска № 5")
                .setDirector("Димитрийка Герасимова")
                .setDistrict(district)
                .setTelephone("Директор: 997-70-94")
                .setId(1L);

        when(schoolRepository.findById(1L)).
                thenReturn(Optional.of(school));

        SchoolEntity newSchool = schoolRepository.findById(school.getId()).orElseThrow();

        return newSchool;

    }

    @Test
    void createTestCityBasedOnCityDTO() {
        CityEntity city = (CityEntity) new CityEntity().
                setName("Sofia")
                .setId(1L);

        when(cityRepository.findById(city.getId())).
                thenReturn(Optional.of(city));

        DistrictEntity district = (DistrictEntity) new DistrictEntity().
                setName("Район Витоша").
                setCity(city).
                setId(1L);

        CityEntity newCity = cityRepository.findById(city.getId()).orElseThrow();

        CityDTO cityDTO = new CityDTO().setName(newCity.getName());

        Assertions.assertEquals(cityDTO.getName(), newCity.getName());

    }

    @Test
    void createTestDistrictBasedOnCityDTO() {
        CityEntity testCity1 = createTestCity1();
        DistrictEntity testDistrict1 = createTestDistrict1(testCity1);

        DistrictDTO districtDTO = new DistrictDTO().setName(testDistrict1.getName())
                .setCityName(testDistrict1.getCity().getName());

        Assertions.assertEquals(districtDTO.getName(), testDistrict1.getName());
        Assertions.assertEquals(districtDTO.getCityName(), testDistrict1.getCity().getName());


    }
}

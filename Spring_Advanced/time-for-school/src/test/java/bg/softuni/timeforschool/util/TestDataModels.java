package bg.softuni.timeforschool.util;

import bg.softuni.timeforschool.model.entity.*;
import bg.softuni.timeforschool.model.enums.SchoolProfileEnum;
import bg.softuni.timeforschool.model.enums.UserRoleEnum;
import bg.softuni.timeforschool.repository.*;
import org.springframework.stereotype.Component;

@Component
public class TestDataModels {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private SchoolRepository schoolRepository;
    private SchoolProfileRepository schoolProfileRepository;
    private DistrictRepository districtRepository;
    private CityRepository cityRepository;


    public TestDataModels(UserRepository userRepository, UserRoleRepository userRoleRepository,
                          SchoolRepository schoolRepository,
                          SchoolProfileRepository schoolProfileRepository, DistrictRepository districtRepository,
                          CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.schoolRepository = schoolRepository;
        this.schoolProfileRepository = schoolProfileRepository;
        this.districtRepository = districtRepository;
        this.cityRepository = cityRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        UserEntity admin = new UserEntity().
                setEmail(email).
                setName("Admin Adminov").
                setImageUrl(null).
                setActive(true).
                setUserRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public UserEntity createTestUser(String email) {

        initRoles();

        UserEntity user = new UserEntity().
                setEmail(email).
                setName("User Userov").
                setImageUrl(null).
                setActive(true).
                setUserRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getUserRole() != UserRoleEnum.ADMIN).
                        toList());

        return userRepository.save(user);
    }

    public CityEntity createTestCity() {
        CityEntity city = new CityEntity().
                setName("Sofia");

        return cityRepository.save(city);
    }

    public DistrictEntity createTestDistrict(CityEntity cityEntity) {
        DistrictEntity district = new DistrictEntity().
                setName("Район Витоша").
                setCity(cityEntity);

        return districtRepository.save(district);
    }

    public void createTestSchoolProfiles() {
        SchoolProfileEntity schoolProfile1 = new SchoolProfileEntity()
                .setProfile(SchoolProfileEnum.ECONOMICS);

        SchoolProfileEntity schoolProfile2 = new SchoolProfileEntity()
                .setProfile(SchoolProfileEnum.FRENCH_LANGUAGE);

        schoolProfileRepository.save(schoolProfile1);
        schoolProfileRepository.save(schoolProfile2);

    }

    public SchoolEntity createTestSchool(CityEntity city,
                                         DistrictEntity district) {

        createTestSchoolProfiles();

        SchoolEntity school = new SchoolEntity()
                .setName("78 СУ Христо Смирненски")
                .setSchoolProfiles(schoolProfileRepository.findAll())
                .setSchoolUrl("http://www.78sou.net/")
                .setCity(city)
                .setEmail("sou_78@abv.bg")
                .setAddress("ул. Царибродска № 5")
                .setDirector("Димитрийка Герасимова")
                .setDistrict(district)
                .setTelephone("Директор: 997-70-94");

        return schoolRepository.save(school);
    }

    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        schoolProfileRepository.deleteAll();
        schoolRepository.deleteAll();
        districtRepository.deleteAll();
        cityRepository.deleteAll();
    }
}

package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.model.dto.CreateOrUpdateOfferDTO;
import bg.softuni.timeforschool.model.entity.DistrictEntity;
import bg.softuni.timeforschool.model.entity.OfferEntity;
import bg.softuni.timeforschool.model.entity.UserEntity;
import bg.softuni.timeforschool.model.entity.UserRoleEntity;
import bg.softuni.timeforschool.model.enums.UserRoleEnum;
import bg.softuni.timeforschool.model.user.TimeForSchoolUserDetails;
import bg.softuni.timeforschool.repository.OfferRepository;
import bg.softuni.timeforschool.repository.UserRepository;
import bg.softuni.timeforschool.service.TimeForSchoolUserDetailsService;
import bg.softuni.timeforschool.util.TestDataModels;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class OfferControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OfferRepository mockOfferRepo;

    @Mock
    private UserRepository mockUserRepo;

    private TimeForSchoolUserDetailsService toTest;

    @Autowired
    private TestDataModels testDataModels;

    @BeforeEach
    void setUp() {
        toTest = new TimeForSchoolUserDetailsService(mockUserRepo);
    }

    @AfterEach
    void tearDown() {
        testDataModels.cleanUpDatabase();
    }

    @Test
    void testOfferAddPageShown() throws Exception {
        mockMvc.perform(get("/offers/add")).
                andExpect(status().isOk()).
                andExpect(view().name("offer-add"));
    }

    private OfferEntity buildUserAndOffer() {
        UserEntity testUserEntity =
                new UserEntity().
                        setEmail("pesho@example.com").
                        setPassword("topsecret").
                        setName("Pesho").
                        setActive(true).
                        setImageUrl("http://image.com/image").
                        setUserRoles(
                                List.of(
                                        new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN),
                                        new UserRoleEntity().setUserRole(UserRoleEnum.USER)
                                )
                        );

        when(mockUserRepo.findByEmail(testUserEntity.getEmail())).
                thenReturn(Optional.of(testUserEntity));

        CreateOrUpdateOfferDTO addOfferDTO = new CreateOrUpdateOfferDTO().
                setContact("0888112233").
                setCourse("Математика").
                setDescription("курс за 4-ти клас").
                setExpiryDate(LocalDate.now()).
                setId(1L).
                setSellerName("Pesho");

        ModelMapper modelMapper = new ModelMapper();


        OfferEntity newOffer = modelMapper.map(addOfferDTO, OfferEntity.class);
        UserEntity seller = mockUserRepo.findByEmail("pesho@example.com").
                orElseThrow();

        newOffer.setSeller(seller);

        TimeForSchoolUserDetails userDetails = (TimeForSchoolUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        mockOfferRepo.save(newOffer);

        when(mockOfferRepo.findById(newOffer.getId())).
                thenReturn(Optional.of(newOffer));

        return mockOfferRepo.findById(newOffer.getId()).orElseThrow();
    }

}

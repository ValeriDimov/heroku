package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.exception.ObjectNotFoundException;
import bg.softuni.timeforschool.model.dto.*;
import bg.softuni.timeforschool.model.entity.*;
import bg.softuni.timeforschool.model.enums.UserRoleEnum;
import bg.softuni.timeforschool.model.user.TimeForSchoolUserDetails;
import bg.softuni.timeforschool.repository.OfferRepository;
import bg.softuni.timeforschool.repository.UserRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @Mock
    private OfferRepository mockOfferRepo;

    @Mock
    private UserRepository mockUserRepo;

    private TimeForSchoolUserDetailsService toTest;


    @BeforeEach
    void setUp() {
        toTest = new TimeForSchoolUserDetailsService(mockUserRepo);
    }


    @Test
    void testLoadOfferByUserExists() {
        // arrange
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


        // act
        TimeForSchoolUserDetails userDetails = (TimeForSchoolUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        mockOfferRepo.save(newOffer);

        // assert
        Assertions.assertEquals(addOfferDTO.getCourse(), newOffer.getCourse());
        Assertions.assertEquals(addOfferDTO.getContact(), newOffer.getContact());
        Assertions.assertEquals(addOfferDTO.getDescription(), newOffer.getDescription());
        Assertions.assertEquals(addOfferDTO.getExpiryDate(), newOffer.getExpiryDate());
        Assertions.assertEquals(addOfferDTO.getSellerName(), newOffer.getSeller().getName());

    }

    @Test
    void createTestOfferBasedOnOfferDetailDTO() {

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

        OfferEntity offer = (OfferEntity) new OfferEntity().
                setContact("0988712233").
                setCourse("БЕЛ").
                setDescription("Подготвителен курс за 7-ми клас").
                setSeller(testUserEntity).
                setId(1L);

        when(mockOfferRepo.findById(offer.getId())).
                thenReturn(Optional.of(offer));

        OfferEntity newOffer = mockOfferRepo.findById(offer.getId()).orElseThrow();

        OfferDetailDTO offerDetailDTO = new OfferDetailDTO().
                setContact(newOffer.getContact()).
                setCourse(newOffer.getCourse()).
                setDescription(newOffer.getDescription()).
                setSellerName(newOffer.getSeller().getName()).
                setId(newOffer.getId());

        Assertions.assertEquals(offerDetailDTO.getContact(), newOffer.getContact());
        Assertions.assertEquals(offerDetailDTO.getCourse(), newOffer.getCourse());
        Assertions.assertEquals(offerDetailDTO.getDescription(), newOffer.getDescription());
        Assertions.assertEquals(offerDetailDTO.getSellerName(), newOffer.getSeller().getName());
        Assertions.assertEquals(offerDetailDTO.getId(), newOffer.getId());

    }

    @Test
    void createTestOfferBasedOnSearchOfferDTO() {
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

        OfferEntity offer = (OfferEntity) new OfferEntity().
                setContact("0988712233").
                setCourse("БЕЛ").
                setDescription("Подготвителен курс за 7-ми клас").
                setSeller(testUserEntity).
                setExpiryDate(LocalDate.now()).
                setForDeletion(false).
                setId(1L);

        when(mockOfferRepo.findById(offer.getId())).
                thenReturn(Optional.of(offer));

        OfferEntity newOffer = mockOfferRepo.findById(offer.getId()).orElseThrow();

        SearchOfferDTO searchOfferDTO = new SearchOfferDTO().
                setContact(newOffer.getContact()).
                setCourse(newOffer.getCourse()).
                setDescription(newOffer.getDescription()).
                setSeller(newOffer.getSeller().getName());

        boolean dtoEmpty = searchOfferDTO.isEmpty();

        Assertions.assertEquals(searchOfferDTO.getContact(), newOffer.getContact());
        Assertions.assertEquals(searchOfferDTO.getCourse(), newOffer.getCourse());
        Assertions.assertEquals(searchOfferDTO.getDescription(), newOffer.getDescription());
        Assertions.assertEquals(searchOfferDTO.getSeller(), newOffer.getSeller().getName());
        Assertions.assertFalse(dtoEmpty);

    }


}

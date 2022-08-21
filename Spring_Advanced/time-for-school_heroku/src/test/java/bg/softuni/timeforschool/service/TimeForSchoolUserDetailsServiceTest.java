package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.entity.UserEntity;
import bg.softuni.timeforschool.model.entity.UserRoleEntity;
import bg.softuni.timeforschool.model.enums.UserRoleEnum;
import bg.softuni.timeforschool.model.user.TimeForSchoolUserDetails;
import bg.softuni.timeforschool.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeForSchoolUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private TimeForSchoolUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new TimeForSchoolUserDetailsService(mockUserRepo);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
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

        // act
        TimeForSchoolUserDetails userDetails = (TimeForSchoolUserDetails)
                toTest.loadUserByUsername(testUserEntity.getEmail());

        // assert
        Assertions.assertEquals(testUserEntity.getEmail(),
                userDetails.getUsername());
        Assertions.assertEquals(testUserEntity.getName(), userDetails.getName());
        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        Assertions.assertEquals(2, authorities.size());

        Iterator<? extends GrantedAuthority> authoritiesIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),
                authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {

        // arrange
        // NOTE: No need to arrange anything, mocks return empty optionals.

        // act && assert
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("non-existant@example.com"));
    }
}

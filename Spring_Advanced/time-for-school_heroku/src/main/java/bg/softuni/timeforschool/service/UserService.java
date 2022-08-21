package bg.softuni.timeforschool.service;

import bg.softuni.timeforschool.model.dto.UserRegisterDTO;
import bg.softuni.timeforschool.model.entity.UserEntity;
import bg.softuni.timeforschool.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserDetailsService userDetailsService;
    private final EmailService emailService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper,
                       UserDetailsService userDetailsService,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
    }

    public void createUserIfNotExist(String email) {

        var userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            var newUser = new UserEntity().
                    setEmail(email).
                    setPassword(null).
                    setName("New User").
                    setUserRoles(List.of());
            userRepository.save(newUser);
        }
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO,
                                 Locale preferredLocale) {

        UserEntity newUser = modelMapper.map(userRegisterDTO, UserEntity.class);

        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));


        this.userRepository.save(newUser);
        login(newUser.getEmail());
        emailService.sendRegistrationEmail(newUser.getEmail(),
                newUser.getName(),
                preferredLocale);
    }

    public boolean passMatcher(String pass1, String pass2) {

        return pass1.equals(pass2);
    }


    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

}

package bg.softuni.Mine_mobilelele.service;

import bg.softuni.Mine_mobilelele.models.DTOs.UserLoginDTO;
import bg.softuni.Mine_mobilelele.models.DTOs.UserRegisterDTO;
import bg.softuni.Mine_mobilelele.models.UserEntity;
import bg.softuni.Mine_mobilelele.repository.UserRepository;
import bg.softuni.Mine_mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(userLoginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            LOGGER.info("User with not found. User name: {}",
                    userLoginDTO.getUsername());

            return false;
        }

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = optionalUser.get().getPassword();

        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);

        if (success) {
            loginUser(optionalUser.get());
            return true;

        } else {
            logout();
            return false;
        }
    }

    public void logoutUser() {
        logout();
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO){

        if (userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(userRegisterDTO.getFirstName());
            userEntity.setLastName(userRegisterDTO.getLastName());
            userEntity.setEmail(userRegisterDTO.getEmail());
            userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
            userEntity.setActive(true);

            userEntity = userRepository.save(userEntity);

            loginUser(userEntity);
        }

    }

    private void loginUser(UserEntity userEntity) {
        currentUser.setLoggedIn(true);
        currentUser.setName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    private void logout() {
        currentUser.clear();
    }
}

package bg.softuni.battleships.services;

import bg.softuni.battleships.models.CategoryEntity;
import bg.softuni.battleships.models.CurrentUser;
import bg.softuni.battleships.models.ShipEntity;
import bg.softuni.battleships.models.UserEntity;
import bg.softuni.battleships.models.dtos.AddShipDTO;
import bg.softuni.battleships.models.dtos.UserLoginDTO;
import bg.softuni.battleships.models.dtos.UserRegisterDTO;
import bg.softuni.battleships.models.enums.CategoryName;
import bg.softuni.battleships.repositories.CategoryRepository;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;

    public UserService(UserRepository userRepository, CurrentUser currentUser, ShipRepository shipRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean userRegister(UserRegisterDTO userRegisterDTO) {

        Optional<UserEntity> optionalUserByUsername = userRepository.findAllByUsername(userRegisterDTO.getUsername());
        Optional<UserEntity> optionalUserByEmail = userRepository.findAllByEmail(userRegisterDTO.getEmail());
        if (optionalUserByUsername.isPresent()) {
            return false;
        }

        if (optionalUserByEmail.isPresent()) {
            return false;
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setUsername(userRegisterDTO.getUsername());
        user.setFullName(userRegisterDTO.getFullName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());

        this.userRepository.save(user);

        return true;
    }

    public boolean loggedUser(UserLoginDTO userLoginDTO) {

        Optional<UserEntity> byUsername = this.userRepository.findAllByUsername(userLoginDTO.getUsername());

        if (byUsername.isEmpty()) {
            return false;
        }

        String optionalUserPassword = byUsername.get().getPassword();

        if (!optionalUserPassword.equals(userLoginDTO.getPassword())) {
            return false;
        }

        this.currentUser.isLoggedIn();
        this.currentUser.setFullName(byUsername.get().getFullName());
        this.currentUser.setId(byUsername.get().getId());

        return true;
    }

    public boolean userNameIsEmpty(UserLoginDTO userLoginDTO) {
        return userLoginDTO.getUsername().isEmpty();
    }

    public boolean addShip(AddShipDTO addShipDTO) {
        if (!this.currentUser.isLoggedIn()) {
            return false;
        }

        long currentUserId = this.currentUser.getId();

        Optional<ShipEntity> optionalShip = this.shipRepository.findByName(addShipDTO.getName());

        if (optionalShip.isPresent()) {
            return false;
        }

        ShipEntity ship = new ShipEntity();
        ship.setName(addShipDTO.getName());
        ship.setHealth(addShipDTO.getHealth());
        ship.setPower(addShipDTO.getPower());

        String shipDTOCategory = addShipDTO.getCategory();
        CategoryName tempCategory = null;

        switch (shipDTOCategory){
            case "BATTLE":
                tempCategory = CategoryName.BATTLE;;
                break;
            case "CARGO":
                tempCategory = CategoryName.CARGO;;
                break;
            case "PATROL":
                tempCategory = CategoryName.PATROL;
                break;
        }

        Optional<CategoryEntity> optionalCategory = this.categoryRepository.findByName(tempCategory);

            ship.setCategory(optionalCategory.get());
            ship.setCreated(addShipDTO.getCreated());

        Optional<UserEntity> byId = this.userRepository.findById(currentUserId);

        ship.setUser(byId.get());

        this.shipRepository.save(ship);

        return true;
    }

    public void logout(){
        this.currentUser.clear();
    }
}

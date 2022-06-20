package bg.softuni.battleships.services;

import bg.softuni.battleships.models.CurrentUser;
import bg.softuni.battleships.models.ShipEntity;
import bg.softuni.battleships.models.UserEntity;
import bg.softuni.battleships.models.dtos.AllShipsDTO;
import bg.softuni.battleships.models.dtos.AttackerShipDTO;
import bg.softuni.battleships.models.dtos.DefenderShipsDTO;
import bg.softuni.battleships.models.dtos.FireDTO;
import bg.softuni.battleships.repositories.ShipRepository;
import bg.softuni.battleships.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public ShipService(ShipRepository shipRepository, UserRepository userRepository, CurrentUser currentUser) {
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

   public List<AttackerShipDTO> getAttackerShips(){
       long userId = this.currentUser.getId();
       Optional<UserEntity> user = this.userRepository.findById(userId);

       List<ShipEntity> attackerShips = this.shipRepository.findAllByUser(user.get());

       List<AttackerShipDTO> attackerShipDTOS = new ArrayList<>();

       for (int i = 0; i < attackerShips.size(); i++) {
           AttackerShipDTO attackerShipDTO = new AttackerShipDTO();
           attackerShipDTO.setId(attackerShips.get(i).getId());
           attackerShipDTO.setName(attackerShips.get(i).getName());
           attackerShipDTO.setHealth(attackerShips.get(i).getHealth());
           attackerShipDTO.setPower(attackerShips.get(i).getPower());

           attackerShipDTOS.add(attackerShipDTO);
       }

        return attackerShipDTOS;
   }

    public List<DefenderShipsDTO> getDefenderShips(){
        long userId = this.currentUser.getId();
        Optional<UserEntity> user = this.userRepository.findById(userId);

        List<ShipEntity> defenderShips = this.shipRepository.findAllByUserIsNot(user.get());

        List<DefenderShipsDTO> defenderShipsDTOS = new ArrayList<>();

        for (int i = 0; i < defenderShips.size(); i++) {
            DefenderShipsDTO defenderShipsDTO = new DefenderShipsDTO();
            defenderShipsDTO.setId(defenderShips.get(i).getId());
            defenderShipsDTO.setName(defenderShips.get(i).getName());
            defenderShipsDTO.setHealth(defenderShips.get(i).getHealth());
            defenderShipsDTO.setPower(defenderShips.get(i).getPower());

            defenderShipsDTOS.add(defenderShipsDTO);
        }

        return defenderShipsDTOS;

    }
    public List<AllShipsDTO> getAllShips(){


        List<ShipEntity> allShips = this.shipRepository.findAll();

        List<AllShipsDTO> allShipsDTOS = new ArrayList<>();

        for (int i = 0; i < allShips.size(); i++) {
            AllShipsDTO allShipsDTO = new AllShipsDTO();
            allShipsDTO.setId(allShips.get(i).getId());
            allShipsDTO.setName(allShips.get(i).getName());
            allShipsDTO.setHealth(allShips.get(i).getHealth());
            allShipsDTO.setPower(allShips.get(i).getPower());

            allShipsDTOS.add(allShipsDTO);
        }

        return allShipsDTOS;
    }

    public void fireNow(FireDTO fireDTO) {
        ShipEntity attackerShip = fireDTO.getAttackerShipId();
        ShipEntity defenderShip = fireDTO.getDefenderShipId();

        if (attackerShip.getPower() >= defenderShip.getHealth()) {
            this.shipRepository.delete(defenderShip);

        } else {
           long temp = defenderShip.getHealth() - attackerShip.getPower();
           defenderShip.setHealth(0);
            this.shipRepository.save(defenderShip);
            defenderShip.setHealth(temp);
            this.shipRepository.save(defenderShip);

        }

    }




}

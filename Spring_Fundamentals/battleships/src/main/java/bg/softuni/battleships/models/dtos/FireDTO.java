package bg.softuni.battleships.models.dtos;

import bg.softuni.battleships.models.ShipEntity;

public class FireDTO {

    private ShipEntity attackerShipId;

    private String attackerShipName;

    private ShipEntity defenderShipId;

    private String defenderShipName;

    public String getAttackerShipName() {
        return attackerShipName;
    }

    public FireDTO setAttackerShipName(String attackerShipName) {
        this.attackerShipName = attackerShipName;
        return this;
    }

    public String getDefenderShipName() {
        return defenderShipName;
    }

    public FireDTO setDefenderShipName(String defenderShipName) {
        this.defenderShipName = defenderShipName;
        return this;
    }

    public FireDTO() {
    }

    public ShipEntity getAttackerShipId() {
        return attackerShipId;
    }

    public FireDTO setAttackerShipId(ShipEntity attackerShipId) {
        this.attackerShipId = attackerShipId;
        return this;
    }

    public ShipEntity getDefenderShipId() {
        return defenderShipId;
    }

    public FireDTO setDefenderShipId(ShipEntity defenderShipId) {
        this.defenderShipId = defenderShipId;
        return this;
    }
}

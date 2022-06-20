package bg.softuni.battleships.models.dtos;

import bg.softuni.battleships.models.ShipEntity;

import javax.persistence.Column;
import java.util.List;

public class AttackerShipDTO {

    private long id;

    private String name;

    private long health;

    private long power;

    public AttackerShipDTO() {
    }

    public long getId() {
        return id;
    }

    public AttackerShipDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttackerShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public AttackerShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public AttackerShipDTO setPower(long power) {
        this.power = power;
        return this;
    }
}

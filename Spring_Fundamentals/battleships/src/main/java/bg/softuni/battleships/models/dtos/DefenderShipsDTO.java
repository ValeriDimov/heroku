package bg.softuni.battleships.models.dtos;

import bg.softuni.battleships.models.ShipEntity;

import java.util.List;

public class DefenderShipsDTO {

    private long id;

    private String name;

    private long health;

    private long power;

    public DefenderShipsDTO() {
    }

    public long getId() {
        return id;
    }

    public DefenderShipsDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DefenderShipsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public DefenderShipsDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public DefenderShipsDTO setPower(long power) {
        this.power = power;
        return this;
    }
}

package bg.softuni.battleships.models.dtos;

public class AllShipsDTO {

    private long id;

    private String name;

    private long health;

    private long power;

    public AllShipsDTO() {
    }

    public long getId() {
        return id;
    }

    public AllShipsDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AllShipsDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public AllShipsDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public AllShipsDTO setPower(long power) {
        this.power = power;
        return this;
    }

    @Override
    public String toString() {
        return name + " | " + health + " | " + power;
    }
}

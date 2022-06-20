package bg.softuni.battleships.models.dtos;

import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddShipDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Positive
    private long health;
    @Positive
    private long power;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    private String category;

    public AddShipDTO() {
    }

    public String getName() {
        return name;
    }

    public AddShipDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public AddShipDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public AddShipDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public AddShipDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public AddShipDTO setCategory(String category) {
        this.category = category;
        return this;
    }
}

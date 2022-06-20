package bg.softuni.battleships.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class ShipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private long health;

    @Column(nullable = false)
    private long power;

    @Column(nullable = false)
    private LocalDate created;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity user;

    public ShipEntity() {
    }

    public long getId() {
        return id;
    }

    public ShipEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public ShipEntity setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public ShipEntity setPower(long power) {
        this.power = power;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }


}

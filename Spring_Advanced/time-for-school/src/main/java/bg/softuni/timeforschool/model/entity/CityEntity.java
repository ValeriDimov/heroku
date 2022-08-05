package bg.softuni.timeforschool.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public CityEntity setName(String name) {
        this.name = name;
        return this;
    }
}

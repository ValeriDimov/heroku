package bg.softuni.timeforschool.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "districts")
public class DistrictEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private CityEntity city;

    public String getName() {
        return name;
    }

    public DistrictEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CityEntity getCity() {
        return city;
    }

    public DistrictEntity setCity(CityEntity city) {
        this.city = city;
        return this;
    }
}

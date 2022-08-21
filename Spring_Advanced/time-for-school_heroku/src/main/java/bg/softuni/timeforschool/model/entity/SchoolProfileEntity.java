package bg.softuni.timeforschool.model.entity;

import bg.softuni.timeforschool.model.enums.SchoolProfileEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "school_profiles")
public class SchoolProfileEntity extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SchoolProfileEnum profile;

    @ManyToMany
    private List<SchoolEntity> schools;

    public SchoolProfileEnum getProfile() {
        return profile;
    }

    public SchoolProfileEntity setProfile(SchoolProfileEnum profile) {
        this.profile = profile;
        return this;
    }

    public List<SchoolEntity> getSchools() {
        return schools;
    }

    public SchoolProfileEntity setSchools(List<SchoolEntity> schools) {
        this.schools = schools;
        return this;
    }

    @Override
    public String toString() {
        return profile.toString();
    }
}

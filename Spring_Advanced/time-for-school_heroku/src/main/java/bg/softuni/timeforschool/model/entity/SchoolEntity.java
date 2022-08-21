package bg.softuni.timeforschool.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "schools")
public class SchoolEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String director;

    @Column(nullable = false)
    private String address;

    private String telephone;

    private String email;

    @Column(name = "school_url")
    private String schoolUrl;

    @ManyToOne
    private DistrictEntity district;

    @ManyToOne
    private CityEntity city;

    @ManyToMany(targetEntity = SchoolProfileEntity.class, mappedBy = "schools", fetch = FetchType.EAGER)
    private List<SchoolProfileEntity> schoolProfiles;

    public SchoolEntity addProfile(SchoolProfileEntity schoolProfile) {
        this.schoolProfiles.add(schoolProfile);
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public SchoolEntity setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SchoolEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public SchoolEntity setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SchoolEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSchoolUrl() {
        return schoolUrl;
    }

    public SchoolEntity setSchoolUrl(String schoolUrl) {
        this.schoolUrl = schoolUrl;
        return this;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public SchoolEntity setDistrict(DistrictEntity district) {
        this.district = district;
        return this;
    }

    public CityEntity getCity() {
        return city;
    }

    public SchoolEntity setCity(CityEntity city) {
        this.city = city;
        return this;
    }

    public List<SchoolProfileEntity> getSchoolProfiles() {
        return schoolProfiles;
    }

    public SchoolEntity setSchoolProfiles(List<SchoolProfileEntity> schoolProfiles) {
        this.schoolProfiles = schoolProfiles;
        return this;
    }

    @Override
    public String toString() {
        return "SchoolEntity{" +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", schoolUrl='" + schoolUrl + '\'' +
                ", district=" + district +
                ", city=" + city +
                ", schoolProfiles=" + schoolProfiles +
                '}';
    }
}

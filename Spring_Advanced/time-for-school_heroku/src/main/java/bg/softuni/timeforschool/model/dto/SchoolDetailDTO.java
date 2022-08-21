package bg.softuni.timeforschool.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SchoolDetailDTO {

    @NotNull
    @Min(1)
    private Long id;

    @NotEmpty
    private String name;

    private String director;

    @NotEmpty
    private String address;

    private String telephone;

    private String email;

    private String schoolUrl;

    @NotEmpty
    private String districtName;

    @NotEmpty
    private String cityName;

    private List<String> schoolProfiles;

    public Long getId() {
        return id;
    }

    public SchoolDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SchoolDetailDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public SchoolDetailDTO setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SchoolDetailDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public SchoolDetailDTO setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SchoolDetailDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSchoolUrl() {
        return schoolUrl;
    }

    public SchoolDetailDTO setSchoolUrl(String schoolUrl) {
        this.schoolUrl = schoolUrl;
        return this;
    }

    public String getDistrictName() {
        return districtName;
    }

    public SchoolDetailDTO setDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public SchoolDetailDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public List<String> getSchoolProfiles() {
        return schoolProfiles;
    }

    public SchoolDetailDTO setSchoolProfiles(List<String> schoolProfiles) {
        this.schoolProfiles = schoolProfiles;
        return this;
    }
}

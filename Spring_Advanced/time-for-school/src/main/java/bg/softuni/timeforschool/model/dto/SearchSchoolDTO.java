package bg.softuni.timeforschool.model.dto;

import java.util.List;

public class SearchSchoolDTO {

    private String name;

    private String director;

    private String address;

    private String telephone;

    private String email;

    private String schoolUrl;

    private String districtName;

    private String cityName;

    private String profile;


    public String getProfile() {
        return profile;
    }

    public SearchSchoolDTO setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getName() {
        return name;
    }

    public SearchSchoolDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDirector() {
        return director;
    }

    public SearchSchoolDTO setDirector(String director) {
        this.director = director;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SearchSchoolDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public SearchSchoolDTO setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SearchSchoolDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSchoolUrl() {
        return schoolUrl;
    }

    public SearchSchoolDTO setSchoolUrl(String schoolUrl) {
        this.schoolUrl = schoolUrl;
        return this;
    }

    public String getDistrictName() {
        return districtName;
    }

    public SearchSchoolDTO setDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public SearchSchoolDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }


    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                districtName == null || districtName.isEmpty() &&
                cityName == null || cityName.isEmpty() &&
                profile == null || profile.isEmpty();
    }
}

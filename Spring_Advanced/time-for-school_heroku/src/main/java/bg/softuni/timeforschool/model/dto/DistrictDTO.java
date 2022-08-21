package bg.softuni.timeforschool.model.dto;

public class DistrictDTO {

    private String name;

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public DistrictDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getName() {
        return name;
    }

    public DistrictDTO setName(String name) {
        this.name = name;
        return this;
    }
}

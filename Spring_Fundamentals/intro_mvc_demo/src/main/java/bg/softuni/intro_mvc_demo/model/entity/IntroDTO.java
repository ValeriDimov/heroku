package bg.softuni.intro_mvc_demo.model.entity;

public class IntroDTO {

    private String name;

    public String getName() {
        return name;
    }

    public IntroDTO setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "IntroDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

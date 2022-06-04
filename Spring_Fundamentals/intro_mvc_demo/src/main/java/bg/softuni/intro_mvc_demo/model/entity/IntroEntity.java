package bg.softuni.intro_mvc_demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "intros")
public class IntroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public IntroEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IntroEntity setName(String name) {
        this.name = name;
        return this;
    }
}

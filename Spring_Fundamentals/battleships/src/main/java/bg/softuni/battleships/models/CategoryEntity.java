package bg.softuni.battleships.models;

import bg.softuni.battleships.models.enums.CategoryName;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, unique = true)
    private CategoryName name;

    @Column(columnDefinition = "text")
    private String description;

    public CategoryEntity() {
    }

    public long getId() {
        return id;
    }

    public CategoryEntity setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryName getName() {
        return name;
    }

    public CategoryEntity setName(CategoryName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}

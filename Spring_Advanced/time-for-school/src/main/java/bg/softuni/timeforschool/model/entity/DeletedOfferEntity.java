package bg.softuni.timeforschool.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "deleted_offers")
public class DeletedOfferEntity extends BaseEntity {

    private String course;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String contact;

    private LocalDate expiryDate;

    public String getCourse() {
        return course;
    }

    public DeletedOfferEntity setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeletedOfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public DeletedOfferEntity setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public DeletedOfferEntity setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }
}

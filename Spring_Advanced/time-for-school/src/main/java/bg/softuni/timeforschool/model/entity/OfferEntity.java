package bg.softuni.timeforschool.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @Column(nullable = false)
    private String course;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contact;

    private LocalDate expiryDate;

    private boolean forDeletion = false;

    @ManyToOne
    private UserEntity seller;

    public String getCourse() {
        return course;
    }

    public OfferEntity setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public OfferEntity setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public UserEntity getSeller() {
        return seller;
    }

    public OfferEntity setSeller(UserEntity seller) {
        this.seller = seller;
        return this;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public OfferEntity setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public boolean isForDeletion() {
        return forDeletion;
    }

    public OfferEntity setForDeletion(boolean forDeletion) {
        this.forDeletion = forDeletion;
        return this;
    }


    @Override
    public String toString() {
        return "OfferEntity{" +
                "course='" + course + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact + '\'' +
                ", seller=" + seller +
                '}';
    }
}

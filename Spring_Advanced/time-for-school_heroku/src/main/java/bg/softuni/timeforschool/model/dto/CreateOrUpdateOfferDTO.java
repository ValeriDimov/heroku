package bg.softuni.timeforschool.model.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class CreateOrUpdateOfferDTO {

    private Long id;

    @NotEmpty
    private String course;

    @NotEmpty
    private String description;

    @NotEmpty
    private String contact;

    private String sellerName;

    private LocalDate expiryDate = LocalDate.now().plusMonths(3L);

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public CreateOrUpdateOfferDTO setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    public String getSellerName() {
        return sellerName;
    }

    public CreateOrUpdateOfferDTO setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CreateOrUpdateOfferDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCourse() {
        return course;
    }

    public CreateOrUpdateOfferDTO setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrUpdateOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public CreateOrUpdateOfferDTO setContact(String contact) {
        this.contact = contact;
        return this;
    }
}


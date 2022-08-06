package bg.softuni.timeforschool.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OfferDetailDTO {

    @NotNull
    @Min(1)
    private Long id;

    @NotEmpty
    private String course;

    @NotEmpty
    private String description;

    @NotEmpty
    private String contact;

    @NotEmpty
    private String sellerName;

    public Long getId() {
        return id;
    }

    public OfferDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCourse() {
        return course.toUpperCase();
    }

    public OfferDetailDTO setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferDetailDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public OfferDetailDTO setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getSellerName() {
        return sellerName;
    }

    public OfferDetailDTO setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

}

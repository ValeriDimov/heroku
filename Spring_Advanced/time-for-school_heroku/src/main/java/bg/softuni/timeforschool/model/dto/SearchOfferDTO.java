package bg.softuni.timeforschool.model.dto;

public class SearchOfferDTO {

    private String course;

    private String description;

    private String contact;

    private String seller;

    public String getCourse() {
        return course;
    }

    public SearchOfferDTO setCourse(String course) {
        this.course = course;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SearchOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public SearchOfferDTO setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public SearchOfferDTO setSeller(String seller) {
        this.seller = seller;
        return this;
    }

    public boolean isEmpty() {
        return (course == null || course.isEmpty()) &&
                description == null || description.isEmpty() &&
                seller == null || seller.isEmpty();
    }

    @Override
    public String toString() {
        return "SearchOfferDTO{" +
                "course='" + course + '\'' +
                ", description='" + description + '\'' +
                ", contact='" + contact + '\'' +
                ", seller='" + seller + '\'' +
                '}';
    }
}

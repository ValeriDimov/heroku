package bg.softuni.timeforschool.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateOrUpdateOfferDTO {

//    @NotNull
//    @Min(1)
//    private Long courseId;

    @NotEmpty
    private String course;

    @NotEmpty
    private String description;

    @NotEmpty
    private String contact;

//    public Long getCourseId() {
//        return courseId;
//    }
//
//    public CreateOrUpdateOfferDTO setCourseId(Long courseId) {
//        this.courseId = courseId;
//        return this;
//    }

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


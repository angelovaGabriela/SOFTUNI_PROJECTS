package softuni.exam.models.entities;

import softuni.exam.models.entities.BaseEntity;
import softuni.exam.models.enums.RatingEnum;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {

    @Size(min = 3, max = 19)
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3, max = 19)
    @Column(name = "last_name")
    private String lastName;

    // @Email
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RatingEnum rating;

    @Column(nullable = false)
    private String town;

    public Seller() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public void setRating(RatingEnum rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}

package softuni.exam.models.dtos.importXML;

import softuni.exam.models.enums.RatingEnum;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSellerDTO {

    @Size(min = 3, max = 19)
    @XmlElement(name = "first-name")
    private String firstName;

    @Size(min = 3, max = 19)
    @XmlElement(name = "last-name")
    private String lastName;

    @Email
    @XmlElement
    private String email;



    @XmlElement
    @NotNull
    private RatingEnum rating;
    @XmlElement
    private String town;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public RatingEnum getRating() {
        return rating;
    }

    public String getTown() {
        return town;
    }
}

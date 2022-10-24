package softuni.exam.models.dtos.importXML.tickets;

import javax.validation.constraints.Email;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerEmailDTO {

    @XmlElement
    @Email
    private String email;

    public String getEmail() {
        return email;
    }
}

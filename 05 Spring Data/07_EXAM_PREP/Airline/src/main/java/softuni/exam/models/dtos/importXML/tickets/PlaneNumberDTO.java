package softuni.exam.models.dtos.importXML.tickets;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneNumberDTO {

    @XmlElement(name = "register-number")
    private String registerNumber;

    public String getRegisterNumber() {
        return registerNumber;
    }


}

package softuni.exam.models.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarIdDTO {
    @XmlElement
    private long id;

    public long getId() {
        return id;
    }
}
